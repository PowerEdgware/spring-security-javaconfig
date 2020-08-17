package com.study.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.utils.IPUtil;

@Controller
public class FilesController extends BaseController {
	static String monthKey = "__month__";
	static String filePosition = "/upload/";

	@Value("${file.ext}")
	String fileExt = ".pdf";

	@GetMapping({ "/{rnd}"})
	public String index(@PathVariable("rnd") String rnd) {
		logg("random path="+rnd);
		return "redirect:/index";
	}
	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		// String id=this.request.getSession().getId();
		// 列出文件夹
		List<String> monthCatelog=listFile("", true);
		monthCatelog.sort((x,y)->{
			return x.compareTo(y);
		});
		model.addAttribute("items", monthCatelog);
		return "list";
	}

	@GetMapping("/list_month/{m}")
	public String listMonthFiles(@PathVariable(name = "m") String month,Model model) {
		if (!StringUtils.isNumeric(month)) {
			logg("month error=" + month);
			return "list_month";
		}
		// 列出文件
		List<String> monthFiles=listFile(month, false);
		model.addAttribute("items", monthFiles);

		request.getSession().setAttribute(monthKey, month);

		return "list_month";
	}

	@ResponseBody
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam("fn") String fn) throws IOException {
		String path = request.getServletContext().getRealPath(filePosition);
		Object month = request.getSession().getAttribute(monthKey);
		String filePos = path + File.separator + month + File.separator + fn;
		logg("download file="+filePos+" clientIP="+IPUtil.getRemortIP(request));
		
		File file = new File(filePos);
		org.springframework.core.io.Resource body = new FileSystemResource(file);
		String filename = URLEncoder.encode(file.getName(), "UTF-8");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", filename);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<Resource>(body, headers, HttpStatus.OK);
	}

	List<String> listFile(String subPath, boolean dir) {
		// 列出文件
		String parentPath = request.getServletContext().getRealPath(filePosition);
		File file = new File(parentPath + File.separator + subPath);
		File[] files = file.listFiles();
		List<String> fileNames = Stream.of(files).filter(
				x -> dir ? x.isDirectory() 
						&& org.apache.commons.lang3.StringUtils.isNumeric(x.getName())
						: (x.isFile()&& x.getName().endsWith(fileExt)) )
				.map(f -> f.getName())
				.collect(Collectors.toList());
		return fileNames;
	}
}

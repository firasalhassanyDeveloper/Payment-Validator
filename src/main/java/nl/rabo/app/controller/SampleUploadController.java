package nl.rabo.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import nl.rabo.app.service.PaymentValidatorService;

@Controller

public class SampleUploadController extends SpringBootServletInitializer {

	@Autowired
	private PaymentValidatorService validator;
    @RequestMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
        	validator.processFile("");
            String content = new String(file.getBytes());
            return "file name:" + file.getOriginalFilename() + "<br> content:" + content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file name:" + file.getOriginalFilename() +"<br> read file content error.";
    }

   
}

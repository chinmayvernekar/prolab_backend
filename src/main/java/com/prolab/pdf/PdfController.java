package com.prolab.pdf;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/pdf-service")
@CrossOrigin(origins = "*")
public class PdfController {
	
	@Autowired
	private PdfService pdfService;
	
//	@PostMapping("/generate-pdf")
//	public String generatePdf(@RequestBody PDF pdf) throws FileNotFoundException, IOException, JRException {
//		return pdfService.generatePdf(pdf);
//	}
	
	@GetMapping("/generate-pdf")
	public ResponseEntity<byte[]> downloadFile(@RequestBody PDF pdf) throws FileNotFoundException, IOException, JRException {
		return pdfService.downloadFile(pdf);
	}
}

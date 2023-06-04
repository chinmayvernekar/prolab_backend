package com.prolab.pdf;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.ResponseEntity;

import net.sf.jasperreports.engine.JRException;

public interface PdfService {
	
	public String generatePdf(PDF pdf) throws FileNotFoundException, IOException, JRException;
	
	public ResponseEntity<byte[]> downloadFile(PDF pdf) throws FileNotFoundException, IOException, JRException;
}

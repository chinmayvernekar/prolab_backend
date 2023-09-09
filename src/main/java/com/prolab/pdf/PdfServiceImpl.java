package com.prolab.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class PdfServiceImpl implements PdfService {
	
	
	private static String LOGO_PATH = "";
	private static String PRINT_ENV = "";
	
	@PostConstruct
	public void getResourceFromServerPath() {
		 ClassLoader classLoader = getClass().getClassLoader();
		 LOGO_PATH = classLoader.getResource("ProLab.PNG").getPath();
		 PRINT_ENV = classLoader.getResource("considerBeforePrint.PNG").getPath();
	}


	@Override
	public ResponseEntity<byte[]> downloadFile(PDF pdf) throws FileNotFoundException, IOException, JRException {
		// TODO Auto-generated method stub
		String savedFilePath = generatePdf(pdf);
		File file = new File(savedFilePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        String encodedFilename = URLEncoder.encode(pdf.getPdfFileName(), "UTF-8");
        headers.setContentDispositionFormData("attachment", encodedFilename + ".pdf");

        byte[] fileContent = new byte[(int) file.length()];
        try (InputStream inputStream = new FileInputStream(file)) {
            inputStream.read(fileContent);
        }

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileContent.length)
                .body(fileContent);

	}
	
	@Override
	public String generatePdf(PDF pdf) throws FileNotFoundException, IOException, JRException {
		// TODO Auto-generated method stub
		String quotation =   generateQuotationPage(pdf);
		String images_pdf =  mergePDFs(generateImagesPage(pdf),savePdfToServer());
		String saveFilePath = savePdfToServer() + File.separator + "Final_Quotation_PDF.pdf";
		PDFMergerUtility merger = new PDFMergerUtility();
        merger.addSource(quotation);
        merger.addSource(images_pdf);
        merger.setDestinationFileName(saveFilePath);
        merger.mergeDocuments(null);
		
		return saveFilePath;
	}
	
	
	public String generateQuotationPage(PDF pdf) throws FileNotFoundException, JRException {
		 	InputStream inputStream = getClass().getResourceAsStream("/Quotation.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
	        Map<String, Object> parameters = new HashMap<String, Object>();
	        parameters.put("pro_lab_logo", LOGO_PATH);
	        parameters.put("consider_before_print", PRINT_ENV);
	        parameters.put("custName", pdf.getCustomerName());
	        parameters.put("address", pdf.getAddress());
	        parameters.put("contactPerson", pdf.getContactPerson());
	        parameters.put("date", pdf.getDate());
	        parameters.put("qtyNumber", pdf.getQtnNumber());
	        parameters.put("validity", pdf.getValidity());
	        parameters.put("contactNumber", pdf.getContactNo());
	        parameters.put("hsnCode", pdf.getHsnCode());
	        parameters.put("productName", pdf.getProductName());
	        parameters.put("model", pdf.getProductModel());
	        parameters.put("make", pdf.getProductMake());
	        parameters.put("qty", pdf.getQty());
	        parameters.put("unitPrice", pdf.getUnitPrice());
	        parameters.put("offerPrice", pdf.getOfferPrice());
	        parameters.put("supply_line_1", pdf.getSupply_line_1());
	        parameters.put("supply_line_2", pdf.getSupply_line_2());
	        parameters.put("supply_line_3", pdf.getSupply_line_3());
	        parameters.put("tc_prices", parameters);
	        parameters.put("tc_tax", parameters);
	        parameters.put("tc_delivery", parameters);
	        parameters.put("tc_freight", parameters);
	        parameters.put("tc_installation", parameters);
	        parameters.put("tc_approval", parameters);
	        parameters.put("tc_payment", parameters);
	        parameters.put("tc_warranty_line_1", parameters);
	        parameters.put("tc_warranty_line_2", parameters);

	        parameters.put("prolabemail", " prolabindia29@gmail.com");
	        parameters.put("website", " www.prolabindia.in");
	        JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,parameters, new JREmptyDataSource());
	        /* outputStream to create PDF */
	        String fileSavedPath = savePdfToServer() + File.separator + "Quotation_Page.pdf";
	        OutputStream outputStream = new FileOutputStream(new File(fileSavedPath));
	        /* Write content to PDF file */
	        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	        return savePdfToServer() + File.separator + "Quotation_Page.pdf";
	}
	
	public List<String> generateImagesPage(PDF pdf) throws JRException, FileNotFoundException {
		List<String> imagesPdfPath = new ArrayList<>();
		
		if (pdf.getImage_1_path() != null) {
			String path = savePdfToServer() + File.separator + "Image1.pdf";
			InputStream inputStream = getClass().getResourceAsStream("/BlankPage.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
	        Map<String, Object> parameters = new HashMap<String, Object>();
	        parameters.put("imagePath", pdf.getImage_1_path());
	        parameters.put("prolabemail", " prolabindia29@gmail.com");
	        parameters.put("website", " www.prolabindia.in");
	        parameters.put("pro_lab_logo", LOGO_PATH);
	        JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,parameters, new JREmptyDataSource());
	        /* outputStream to create PDF */
	        OutputStream outputStream = new FileOutputStream(new File(path));
	        /* Write content to PDF file */
	        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	        imagesPdfPath.add(path);
		}
		
		if (pdf.getImage_2_path() != null) {
			String path = savePdfToServer() + File.separator + "Image2.pdf";
			InputStream inputStream = getClass().getResourceAsStream("/BlankPage.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
	        Map<String, Object> parameters = new HashMap<String, Object>();
	        parameters.put("imagePath", pdf.getImage_2_path());
	        parameters.put("prolabemail", " prolabindia29@gmail.com");
	        parameters.put("website", " www.prolabindia.in");
	        parameters.put("pro_lab_logo", LOGO_PATH);
	        JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,parameters, new JREmptyDataSource());
	        /* outputStream to create PDF */
	        OutputStream outputStream = new FileOutputStream(new File(path));
	        /* Write content to PDF file */
	        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	        imagesPdfPath.add(path);
		}
		
		if (pdf.getImage_3_path() != null) {
			String path = savePdfToServer() + File.separator + "Image3.pdf";
			InputStream inputStream = getClass().getResourceAsStream("/BlankPage.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
	        Map<String, Object> parameters = new HashMap<String, Object>();
	        parameters.put("imagePath", pdf.getImage_3_path());
	        parameters.put("prolabemail", " prolabindia29@gmail.com");
	        parameters.put("website", " www.prolabindia.in");
	        parameters.put("pro_lab_logo", LOGO_PATH);
	        JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,parameters, new JREmptyDataSource());
	        /* outputStream to create PDF */
	        OutputStream outputStream = new FileOutputStream(new File(path));
	        /* Write content to PDF file */
	        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	        imagesPdfPath.add(path);
		}
		
		if (pdf.getImage_4_path() != null) {
			String path = savePdfToServer() + File.separator + "Image4.pdf";
			InputStream inputStream = getClass().getResourceAsStream("/BlankPage.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
	        Map<String, Object> parameters = new HashMap<String, Object>();
	        parameters.put("imagePath", pdf.getImage_4_path());
	        parameters.put("prolabemail", " prolabindia29@gmail.com");
	        parameters.put("website", " www.prolabindia.in");
	        parameters.put("pro_lab_logo", LOGO_PATH);
	        JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,parameters, new JREmptyDataSource());
	        /* outputStream to create PDF */
	        OutputStream outputStream = new FileOutputStream(new File(path));
	        /* Write content to PDF file */
	        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	        imagesPdfPath.add(path);
		}
		return imagesPdfPath;
	}
	
	
	public String mergePDFs(List<String> pdfPaths, String mergedFilePath) throws IOException {
		String fileSavedPath = mergedFilePath + File.separator + "FINAL.pdf";
	    PDFMergerUtility merger = new PDFMergerUtility();

	    for (String pdfPath : pdfPaths) {
	        merger.addSource(pdfPath);
	    }

	    merger.setDestinationFileName(fileSavedPath);
	    merger.mergeDocuments(null);

	    return fileSavedPath;
	}
	
	public static String savePdfToServer() {
		Path currentDirectory = Paths.get("").toAbsolutePath();
		// Go back to the parent directory
        Path parentDirectory = currentDirectory.getParent();
		String imageFolderPath = parentDirectory + File.separator + "GENERATED_PDF";
        File imageFolder = new File(imageFolderPath);
        if (!imageFolder.exists()) {
            imageFolder.mkdirs();
        }
        return imageFolder.getAbsolutePath();
	}

}

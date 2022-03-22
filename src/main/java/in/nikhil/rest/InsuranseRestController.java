package in.nikhil.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.nikhil.bindings.request.SearchRequest;
import in.nikhil.bindings.response.planResponse;
import in.nikhil.reports.ExcelReport;
import in.nikhil.reports.PdfReportGenerator;
import in.nikhil.service.InsuransePlanService;

@RestController
public class InsuranseRestController {

	@Autowired
	private InsuransePlanService service;
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		DateFormat dateformate= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateformate.format(new Date());
		
		String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=plans_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
		
		List<planResponse> plans = service.searchPlans(null);
		ExcelReport excelExporter=new ExcelReport();
        excelExporter.export(plans,response);  
		
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		DateFormat dateformate= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateformate.format(new Date());
		
		String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=plans_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
		
		List<planResponse> plans = service.searchPlans(null);
		PdfReportGenerator excelExporter=new PdfReportGenerator();
        excelExporter.exportPdf(plans,response);  
		
	}
	
	@PostMapping("/plans")
	public ResponseEntity<List<planResponse>> searchPlans(@RequestBody SearchRequest request){
		  List<planResponse> responses = service.searchPlans(request);
		  return new ResponseEntity<>(responses,HttpStatus.OK);
	}
	
	@GetMapping("palnnames")
	public ResponseEntity<List<String>> getPlanName(){
		List<String> planNames = service.getUniquePlanName();
		return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	
	@GetMapping("palnstatus")
	public ResponseEntity<List<String>> getPlanStatus(){
		List<String> planstatus = service.getUniquePlanStatus();
		return new ResponseEntity<>(planstatus,HttpStatus.OK);
	}
}

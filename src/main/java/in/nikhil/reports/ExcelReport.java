package in.nikhil.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.nikhil.bindings.response.planResponse;

public class ExcelReport {

	public void export(List<planResponse> plans, HttpServletResponse response) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("plans");

		XSSFRow headerrow = sheet.createRow(0);

		headerrow.createCell(0).setCellValue("Plan ID");
		headerrow.createCell(1).setCellValue("Holder NAME");
		headerrow.createCell(2).setCellValue("Holder SSN");
		headerrow.createCell(3).setCellValue("Plan Name");
		headerrow.createCell(4).setCellValue("Plan Status");

		for (int i = 0; i < plans.size(); i++) {
			planResponse plan = plans.get(i);
			XSSFRow datarow = sheet.createRow(i + 1);

			datarow.createCell(0).setCellValue(plan.getPlanId());
			datarow.createCell(1).setCellValue(plan.getPlanHolderName());
			datarow.createCell(2).setCellValue(plan.getPlanHolderSsn());
			datarow.createCell(3).setCellValue(plan.getPlanName());
			datarow.createCell(4).setCellValue(plan.getPlanStatus());
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}

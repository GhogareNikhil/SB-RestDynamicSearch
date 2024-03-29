package in.nikhil.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nikhil.bindings.response.planResponse;

public class PdfReportGenerator {

	public void exportPdf(List<planResponse> plans, HttpServletResponse response)
			throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("List of Palns", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 1.5f, 2.0f, 3.0f, 2.0f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Plan Id", font1));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Holder NAME", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Holder SSN", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font1));
		table.addCell(cell);

		for (planResponse plan : plans) {
			table.addCell(plan.getPlanId() + "");
			table.addCell(plan.getPlanHolderName());
			table.addCell(plan.getPlanHolderSsn() + "");
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
		}

		document.add(table);
		document.close();
	}
}

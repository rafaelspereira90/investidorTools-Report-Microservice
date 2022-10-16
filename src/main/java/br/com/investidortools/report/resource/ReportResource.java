package br.com.investidortools.report.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.investidortools.report.dto.ReportDto;
import br.com.investidortools.report.dto.ReportsDto;
import br.com.investidortools.report.service.ReportService;


@RestController
@RequestMapping("/v1")
public class ReportResource {

	@Autowired
	ReportService reportService;
	
	@GetMapping("/report")
	public ResponseEntity<ReportsDto> getAll() {
		
		return reportService.getAll().map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/report/{id}")
	public ResponseEntity<ReportDto> getReport(@PathVariable(name = "id") Integer id) {
		
		return reportService.getReport(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/report/{id}/download")
	public ResponseEntity<byte[]> downloadReport(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.body(reportService.downloadReport(id));
	}
}

package br.com.investidortools.report.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import br.com.investidortools.report.dto.ReportDto;
import br.com.investidortools.report.dto.ReportsDto;
import br.com.investidortools.report.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
    private ReportRepository reportRepository;
	
	@Autowired
	private AzureService azureService;
	
	@Cacheable(value = "report.list")
	public Optional<ReportsDto> getAll() {
		
		var result = reportRepository.findAllWithCompanyAndUserName();
		ReportsDto response = new ReportsDto();
		response.setReports(new ArrayList<>());
		result.forEach((Object[] r) -> response.getReports().add(new ReportDto(r)));
		return Optional.of(response);
	}

	public Optional<ReportDto> getReport(Integer id) {

		Optional<ReportDto> response = Optional.empty();
		var result = reportRepository.findbyIdWithCompanyAndUserName(id);
		if (result.isPresent()) {
			var obj = (Object[]) result.get()[0];
			response = Optional.of(new ReportDto(obj));
		}
		return response;
	}
	
	public byte[] downloadReport(Integer id) {
		return azureService.downloadReport("RELATÓRIO TESTE.pdf").toByteArray();
	}

}

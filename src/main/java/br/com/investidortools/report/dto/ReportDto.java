package br.com.investidortools.report.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.investidortools.report.utils.DateUtils;
import lombok.Data;

@Data
public class ReportDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer idCompany;
	
	private String nameCompany;
	
	private LocalDateTime releaseDate;
	
	private String author;
	
	private String authorName;
	
	private String tittle;
	
	private BigDecimal price;
	
	private String imgCompany;
	
	public ReportDto (Object[] line) {
		this.id = Integer.valueOf(line[0].toString());
		this.idCompany = Integer.valueOf(line[1].toString());
		this.nameCompany = String.valueOf(line[2].toString());
		this.author = String.valueOf(line[3].toString());
		this.authorName = String.valueOf(line[4].toString());
		this.price = new BigDecimal(line[5].toString());
		this.releaseDate = DateUtils.convertDate(line[6].toString());
		this.tittle = String.valueOf(line[7].toString());
		this.imgCompany = String.valueOf(line[8].toString());
	}

}

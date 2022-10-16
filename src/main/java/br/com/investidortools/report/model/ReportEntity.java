package br.com.investidortools.report.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report", schema="investidortools")
public class ReportEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "id_company")
	private Integer idCompany;
	
	@Column(name = "release_date")
	private LocalDateTime releaseDate;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "tittle")
	private String tittle;
	
	@Column(name = "price")
	private BigDecimal price;
}

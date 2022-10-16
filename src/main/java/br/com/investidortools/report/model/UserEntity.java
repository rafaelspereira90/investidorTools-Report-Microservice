package br.com.investidortools.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "user", schema="investidortools")
public class UserEntity {

	@Column(name = "name")
	private String name;
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "tel")
	private String tel;
	
	@Column(name = "type")
	//private UserTypeEnum type;
	private String type;
	
	@Column(name = "pass")
	private String pass;
}

package br.com.investidortools.report.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.investidortools.report.model.ReportEntity;


public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

	public List<ReportEntity> findAllByOrderByReleaseDateDesc();
	
	@Query(value = "select r.id, r.id_company, c.\"name\" as name_company, r.author, u.\"name\" as user_name, "
			+ "r.price, r.release_date, r.tittle, c.img \r\n"
			+ "from investidortools.report r \r\n"
			+ "inner join investidortools.company c\r\n"
			+ "on r.id_company = c.id\r\n"
			+ "inner join investidortools.\"user\" u \r\n"
			+ "on r.author = u.email \r\n"
			+ "order by r.release_date DESC", nativeQuery = true)
	public List<Object[]> findAllWithCompanyAndUserName();
	
	@Query(value = "select r.id, r.id_company, c.\"name\" as name_company, r.author, u.\"name\" as user_name, "
			+ "r.price, r.release_date, r.tittle, c.img \r\n"
			+ "from investidortools.report r \r\n"
			+ "inner join investidortools.company c\r\n"
			+ "on r.id_company = c.id\r\n"
			+ "inner join investidortools.\"user\" u \r\n"
			+ "on r.author = u.email \r\n"
			+ "where r.id = :id \r\n", nativeQuery = true)
	public Optional<Object[]> findbyIdWithCompanyAndUserName(@Param("id") Integer id);
}

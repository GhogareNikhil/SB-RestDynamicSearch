package in.nikhil.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nikhil.entity.InsuransePlanEntity;

@Repository
public interface InsuransePlanRepository extends JpaRepository<InsuransePlanEntity, Serializable>{
    
	@Query("select distinct planName from InsuransePlanEntity")
	public List<String> getplanName();
	
	@Query("select distinct planStatus from InsuransePlanEntity")
	public List<String> getplanStatus();
}

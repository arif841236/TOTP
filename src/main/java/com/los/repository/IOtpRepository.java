package com.los.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.los.model.OtpModel;

// This interface to connect with database
@Repository("")
public interface IOtpRepository extends JpaRepository<OtpModel, Long> {
	
	List<OtpModel> findByMobileNumber(String mobileNumber);
	
	List<OtpModel> findByOtpIdAndStatus(String otpId, String status);
	
	List<OtpModel> findByMobileNumberAndStatusAndProcessName(String mobileNumber, String status,String processName);
	
	@Query("select o from OtpModel o where o.createdOn >= :date and o.mobileNumber=:mobile and o.status = :status and o.processName = :processName")
	public List<OtpModel> getOtpModel(@Param("date") LocalDateTime date,@Param("mobile") String mobileNumber,@Param("status") String status,@Param("processName")String processName);
}

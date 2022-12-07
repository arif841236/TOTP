package com.indusnet.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.indusnet.model.OtpData;

/**
 * This interface connect the to the database
 */
@Repository
public interface IOtpRepository extends JpaRepository<OtpData, String>{

	public Optional<OtpData> findByMessageId(String messageId);
	public Optional<OtpData> findByTypeValue(String typeValue);
	@Query("select u from OtpData u where u.messageId = :messageId")
	public OtpData getOtpDataByMessageId(@Param("messageId") String messageId);
}

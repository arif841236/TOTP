package com.indusnet.repository;
import java.math.BigInteger;
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
public interface IOtpRepository extends JpaRepository<OtpData, BigInteger>{
	public Optional<OtpData> findByMessageId(Integer messageId);
	public Optional<OtpData> findByTypeValue(String typeValue);
	@Query("select u from OtpData u where u.messageId = :messageId")
	public OtpData getOtpDataByMessageId(@Param("messageId") Integer messageId);
}

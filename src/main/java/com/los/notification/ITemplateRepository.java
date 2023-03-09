package com.los.notification;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITemplateRepository extends JpaRepository<TemplateModel, Integer> {
	 List<TemplateModel> findByNotificationTypeAndProcessName(String notificationType, String processName);
}

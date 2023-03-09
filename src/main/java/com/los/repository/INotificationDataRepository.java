package com.los.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.los.model.NotificationDataBase;

@Repository("postgresql")
public interface INotificationDataRepository extends JpaRepository<NotificationDataBase, Integer> {

}

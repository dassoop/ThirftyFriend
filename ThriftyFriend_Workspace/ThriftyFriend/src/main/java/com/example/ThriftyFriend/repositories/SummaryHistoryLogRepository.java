package com.example.ThriftyFriend.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ThriftyFriend.models.SummaryHistoryLog;

@Repository
public interface SummaryHistoryLogRepository extends CrudRepository<SummaryHistoryLog, Long>
{
	List<SummaryHistoryLog> findByNameOrderByCreatedAt(String name);
}

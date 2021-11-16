package com.example.ThriftyFriend.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ThriftyFriend.models.ListingSummary;


@Repository
public interface ListingSummaryRepository extends CrudRepository<ListingSummary, Long>
{
	List<ListingSummary> findByNameContaining(String productName);
	List<ListingSummary> findAll();
	boolean existsByName(String name);
	ListingSummary findByName(String name);
}

package com.sb.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sb.model.Blogger;

public interface BloggerRepository extends CrudRepository<Blogger, Long>{
	List<Blogger> findByLastName(String lastName);
}

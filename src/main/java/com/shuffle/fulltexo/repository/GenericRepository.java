package com.shuffle.fulltexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.shuffle.fulltexo.entity.GenericEntity;

@NoRepositoryBean
public interface GenericRepository<E extends GenericEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {

}

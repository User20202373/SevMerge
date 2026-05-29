package com.example.SevMerge.project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {



    // 프로젝트 전체 조회(최신순 조회)
    @Query("""
            SELECT p FROM Project p JOIN FETCH p.client ORDER BY p.createdAt DESC
            """)
    List<Project> findAllProject();



    // 프로젝트 상세조회
    @Query("SELECT p FROM Project p JOIN FETCH WHERE p.id =:id")
    Optional<Project> findByProjectId(@Param("id") Long id);

    // 프로젝트 카테고리별 조회
    @Query("SELECT P FROM Project p JOIN FETCH p.client WHERE p.category = :category ORDER BY p.createdAt DESC")
    List<Project> findByCategory(@Param("category") Category category);

    // 키워드 검색
    @Query("SELECT p FROM Project p JOIN FETCH p.client WHERE p.title LIKE %:keyword% OR p.description LIKE %:keyword% ORDER BY p.createdAt DESC")
    List<Project> findByKeyword(@Param("keyword") String keyword);

    // 의뢰인이 등록한 프로젝트 목록
    @Query("SELECT p FROM Project p JOIN FETCH p.client WHERE p.client.id = :clientId ORDER BY p.createdAt DESC")
    List<Project> findALLProjectByClientId(@Param("clientId") Long clientId);



}

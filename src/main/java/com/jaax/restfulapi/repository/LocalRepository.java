package com.jaax.restfulapi.repository;

import com.jaax.restfulapi.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalRepository extends JpaRepository<Local,Long> {
    // Consulta con JPQL
    @Query("SELECT l FROM Local l WHERE l.name = :name")
    Optional<Local> findLocalByNameWithJPQL(String name);

    // Consulta con Inversión de Control
    Optional<Local> findByName(String name);

    Optional<Local> findByNameIgnoreCase(String name);

}

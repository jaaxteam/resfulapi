package com.jaax.restfulapi.repository;

import com.jaax.restfulapi.entity.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class LocalRepositoryTest {

    @Autowired
    LocalRepository localRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Local local =
                Local.builder()
                        .name("Supermarket")
                        .floor("Third Floor")
                        .code("Sup-010-3")
                        .build();
        testEntityManager.persist(local);
    }


    @Test
    public void findLocalByNameIgnoreCaseFound(){
        Optional<Local> local = localRepository.findByNameIgnoreCase("Supermarket");
        assertEquals(local.get().getName(),"Supermarket");
        System.out.println("local.get() = " + local.get());
    }

    @Test
    public void findLocalByNameIgnoreCaseNotFound(){
        Optional<Local> local = localRepository.findByNameIgnoreCase("Cinema");
        assertEquals(local,Optional.empty());
    }


}
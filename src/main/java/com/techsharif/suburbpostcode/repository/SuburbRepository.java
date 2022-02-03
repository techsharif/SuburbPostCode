package com.techsharif.suburbpostcode.repository;

import com.techsharif.suburbpostcode.entity.Suburb;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuburbRepository extends JpaRepository<Suburb, Long> {

    Optional<Suburb> findById(Long id);

    Optional<Suburb> findByPostcode(Long postcode);

    List<Suburb> findAllByPostcodeBetween(Long start, Long end, Sort sort);
}

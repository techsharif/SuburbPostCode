package com.techsharif.suburbpostcode.service;

import com.techsharif.suburbpostcode.entity.Suburb;
import com.techsharif.suburbpostcode.repository.SuburbRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class SuburbServiceImpl implements SuburbService{

    private final SuburbRepository suburbRepository;

    public SuburbServiceImpl(SuburbRepository suburbRepository) {
        this.suburbRepository = suburbRepository;
    }

    @Override
    public Suburb add(Suburb suburb) {
        log.info("Processing suburb create request for suburb: {} postcode {}", suburb.getSuburb(), suburb.getPostcode());
        Optional<Suburb> suburbOptional = suburbRepository.findByPostcode(suburb.getPostcode());
        if (suburbOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A suburb with postcode " + suburbOptional.get().getPostcode() + " is available");
        }
        return suburbRepository.save(suburb);
    }

    @Override
    public List<Suburb> addAll(Stream<Suburb> suburbs) {
        log.info("Processing batch suburb create request");
        try {
            return suburbRepository.saveAll(suburbs.collect(Collectors.toList()));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to process. Please check if there is any Duplicate entry");
        }

    }

    @Override
    public List<Suburb> searchByPostcodeRange(Long start, Long end) {
        if (start > end ){
            Long temp = start;
            start = end;
            end = temp;
        }
        log.info("Fetching data using postcode range {} to {}", start, end);
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "suburb").ignoreCase();
        return suburbRepository.findAllByPostcodeBetween(start, end, Sort.by(order));
    }
}

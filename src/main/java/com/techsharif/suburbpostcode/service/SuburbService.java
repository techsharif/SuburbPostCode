package com.techsharif.suburbpostcode.service;

import com.techsharif.suburbpostcode.entity.Suburb;

import java.util.List;
import java.util.stream.Stream;

public interface SuburbService {

    Suburb add(Suburb suburb);

    List<Suburb> addAll(Stream<Suburb> suburbs);

    List<Suburb> searchByPostcodeRange(Long start, Long end);
}

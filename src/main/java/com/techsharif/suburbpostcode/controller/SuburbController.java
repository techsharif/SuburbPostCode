package com.techsharif.suburbpostcode.controller;

import com.techsharif.suburbpostcode.dto.SuburbDto;
import com.techsharif.suburbpostcode.entity.Suburb;
import com.techsharif.suburbpostcode.service.SuburbService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class SuburbController extends BaseController {

    private final SuburbService suburbService;

    public SuburbController(SuburbService suburbService) {
        this.suburbService = suburbService;
    }

    @PostMapping("/add")
    public Suburb add(@Valid @RequestBody SuburbDto suburbDto){
        return suburbService.add(suburbDto.toEntity());
    }

    @PostMapping("/addAll")
    public List<Suburb> addAll(@Valid @RequestBody List<SuburbDto> suburbDtos){
        return suburbService.addAll(suburbDtos.stream().map(SuburbDto::toEntity));
    }

    @GetMapping("/search/postcode/{start}/{end}")
    public List<Suburb> searchByPostcodeRange(@NotNull @PathVariable Long start, @NotNull @PathVariable Long end){
        return suburbService.searchByPostcodeRange(start, end);
    }

}

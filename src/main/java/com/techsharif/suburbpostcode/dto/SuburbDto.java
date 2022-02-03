package com.techsharif.suburbpostcode.dto;

import com.techsharif.suburbpostcode.entity.Suburb;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class SuburbDto {

    Long id;

    @NotBlank
    @Size(min = 3, max = 25, message = "Suburb must be between 3 and 25 characters")
    private String suburb;

    @NotNull
    private Long postcode;

    public Suburb toEntity(){
        Suburb suburb = new Suburb();
        suburb.setSuburb(this.suburb);
        suburb.setPostcode(this.postcode);
        return suburb;
    }

    public static SuburbDto fromEntity(Suburb suburb){
        return SuburbDto.builder()
                .id(suburb.getId())
                .suburb(suburb.getSuburb())
                .postcode(suburb.getPostcode())
                .build();
    }
}

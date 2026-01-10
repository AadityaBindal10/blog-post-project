package com.BlogPostProject.demo.domain.dtos;

import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequeust {

    @NotBlank(message =  "Category name is required")
    @Size(min = 2, max =  50, message = "Category mmust be between {min} and {max}")
    @Pattern(regexp = "^[\\w\\s-]+$", message = "Category name must only contain letters, numbers, spaces and hyphens" )
    private String name;

}

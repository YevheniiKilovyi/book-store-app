package com.example.bookstoreapp.dto.request.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCategoryRequestDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    private String description;
}

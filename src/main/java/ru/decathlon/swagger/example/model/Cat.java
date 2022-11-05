package ru.decathlon.swagger.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cats")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Meow-meow")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 1, max = 30)
    private String name;
    @Min(1)
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

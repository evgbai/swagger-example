package ru.decathlon.swagger.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.decathlon.swagger.example.model.Cat;
import ru.decathlon.swagger.example.service.CatService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "CatController", description = "One controller to rule all cats.")
public class CatController {

    private final CatService service;

    @Autowired
    public CatController(CatService service) {
        this.service = service;
    }

    @Operation(summary = "You want to see ALL cats.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Show JSON list of cats.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cat.class))
            }),
            @ApiResponse(responseCode = "404", description = "Example: This will never happen!", content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cat>> getCats() {
        var catList = service.getCats();
        return ResponseEntity.ok().body(catList);
    }

    @Operation(summary = "You only want to see ONE cat.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get JSON the cat.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cat.class))
            }),
            @ApiResponse(responseCode = "400", description = "Example: Invalid ID.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Example: Cat not found.", content = @Content)
    })
    @GetMapping(value = "/{catId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cat> getOneCatById(@PathVariable Long catId) {
        var cat = service.getCatById(catId);
        return ResponseEntity.ok().body(cat);
    }

    @Operation(summary = "Saves a kitten to your home.")
    @ApiResponse(responseCode = "201", description = "The kitten is yours now.")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCat(@RequestBody @Valid Cat cat) {
        service.saveCat(cat);
    }

    @Operation(summary = "You gave the cat to the shelter.")
    @ApiResponse(responseCode = "204", description = "You no longer have a cat.")
    @DeleteMapping(value = "/{catId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCatById(@PathVariable Long catId) {
        service.deleteCatById(catId);
    }
}
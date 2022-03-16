package com.sangharsh.oms.controller;

import com.sangharsh.oms.dto.ResultDTO;
import com.sangharsh.oms.exception.ResourceNotFoundException;
import com.sangharsh.oms.model.BaseModel;
import com.sangharsh.oms.repository.BaseRepository;
import com.sangharsh.oms.service.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

public abstract class GenericController<S extends GenericService<? extends BaseRepository<T>, T, U>, T extends BaseModel, U> {
    protected final S service;
    protected GenericController(S service) {
        this.service = service;
    }

    // get all
    @Operation(summary = "Get all objects")
    @GetMapping
    public List<U> getAllEntity() {

        return this.service.findAll();
    }

    @Operation(summary = "Get my stores")
    @GetMapping("/my")
    public ResultDTO<U> getMyEntities(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        return this.service.findByLoggedInUser(page, size);
    }

    // get by id
    @Operation(summary = "Get object by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the object",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RepresentationModel.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Object not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public U getEntityById(@Parameter(description = "id of object to be searched")
                               @PathVariable(value = "id") String auditableId) {
        try{
            return this.service.findById(auditableId).get();
        } catch (ResourceNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Entity Not Found", exc);
        }
    }

    // create Entity
    @Operation(summary = "Create a new object")
    @PostMapping
    public T createEntity(@RequestBody @Valid T object) {

        return this.service.create(object);
    }

    // update Entity
    @Operation(summary = "Update object")
    @PutMapping("/{id}")
    public T updateEntity(@RequestBody U dto, @PathVariable("id") String auditableId) {
        try{
            return this.service.update(auditableId, dto);
        } catch (ResourceNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Entity Not Found", exc);
        }
    }

    // delete Entity by id
    @Operation(summary = "Delete object by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<T> deleteEntity(@Parameter(description = "id of object to be deleted") @PathVariable("id") String auditableId) {
        this.service.delete(auditableId);
        return ResponseEntity.ok().build();
    }
}

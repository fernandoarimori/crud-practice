package com.turn.Into.controller;

import com.turn.Into.model.dto.PersonPostDTO;
import com.turn.Into.model.dto.PersonResponseDTO;
import com.turn.Into.model.repository.PersonRepo;
import com.turn.Into.service.PersonService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepo personRepo;

    @PostMapping
    @Transactional
    public ResponseEntity postPersonCrtl(@RequestBody @Valid PersonPostDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        return personService.postPersonService(dto, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<PersonResponseDTO>> fillAllContrllr(@PageableDefault(size = 10, sort = "name") Pageable page) {
        return personService.getAllPersonService(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneCntrllr(@PathVariable Long id){
        var responserEnt = personService.getOnePersonService(id);
        return responserEnt;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateConmtrllr(@PathVariable Long id, @RequestBody PersonPostDTO dto){
        return personService.updatePersonService(id, dto);
    }

    @DeleteMapping("/harddelete/{id}")
    public ResponseEntity hardDeleteCntrllr (@PathVariable Long id){
        return personService.deletePersonService(id);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity softDelete(@PathVariable Long id){
        return personService.softDelete(id);
    }

}

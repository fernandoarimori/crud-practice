package com.turn.Into.service;

import com.turn.Into.model.Person;
import com.turn.Into.model.dto.PersonPostDTO;
import com.turn.Into.model.dto.PersonResponseDTO;
import com.turn.Into.model.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;

    public ResponseEntity postPersonService(PersonPostDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        var newPerson = new Person(dto);
        var response = personRepo.save(newPerson);
        var uri = uriComponentsBuilder.path("person/{id}").buildAndExpand(newPerson.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonResponseDTO(response));
    }

    public ResponseEntity<Page<PersonResponseDTO>> getAllPersonService(Pageable pageable) {
        return ResponseEntity.ok(personRepo.findAll(pageable).map(PersonResponseDTO::new));
    }

    public ResponseEntity getOnePersonService(Long id) {
        var person = personRepo.getReferenceById(id);
        return ResponseEntity.ok(new PersonResponseDTO(person));
    }

    public ResponseEntity updatePersonService(Long id, PersonPostDTO dto) {
        var personToUpdate = personRepo.getReferenceById(id);
        personToUpdate.update(dto);
        var personUpdated = personRepo.save(personToUpdate);
        return ResponseEntity.ok(new PersonResponseDTO(personUpdated));
    }

    public ResponseEntity deletePersonService(Long id) {
        var toDeletePerson = personRepo.getReferenceById(id);
        if (!toDeletePerson.getActive()==true){
            personRepo.delete(toDeletePerson);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity softDelete(Long id){
        var personToSoftDelete = personRepo.getReferenceById(id);
        personToSoftDelete.softDelete();
        personRepo.save(personToSoftDelete);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}

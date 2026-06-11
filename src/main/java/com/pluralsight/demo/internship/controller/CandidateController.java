package com.pluralsight.demo.internship.controller;

import com.pluralsight.demo.internship.model.Candidate;
import com.pluralsight.demo.internship.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates(
            @RequestParam(required = false) String fieldOfStudy) {

        List<Candidate> candidates /*= new ArrayList<>()*/;

        if (fieldOfStudy != null) {
            candidates = candidateService.getCandidatesByFieldOfStudy(fieldOfStudy);
        } else {
            candidates = candidateService.getAllCandidates();
        }
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Candidate>> searchByName(@PathVariable String Name) {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
//        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/search/name/{email}")
    public ResponseEntity<List<Candidate>> searchByEmail(@PathVariable String email) {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }


@GetMapping("/{id}")
public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
    Candidate candidate = candidateService.getCandidateById(id);
    return ResponseEntity.ok(candidate);
}

@PostMapping
public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
    // Same flaw: returns 200 instead of 201
    Candidate created = candidateService.createCandidate(candidate);
    return ResponseEntity.ok(created);
}

@PutMapping("/{id}")
public ResponseEntity<Candidate> updateCandidate(
        @PathVariable Long id,
        @RequestBody Candidate candidate) {
    Candidate updated = candidateService.updateCandidate(id, candidate);
    return ResponseEntity.ok(updated);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
    candidateService.deleteCandidate(id);
    return ResponseEntity.noContent().build();
}
}

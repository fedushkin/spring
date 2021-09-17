package com.cources.jpa.web.rest;

import com.cources.jpa.domain.Trans;
import com.cources.jpa.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransResource {
    private final TransService transService;

    @Autowired
    public TransResource(TransService transService) {
        this.transService = transService;
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<Trans>> getMessage () {
        return new ResponseEntity<List<Trans>>(transService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Trans> saveTrans (@RequestBody Trans trans) {
        return ResponseEntity.ok(transService.saveEach(trans));
    }

    @PutMapping("/transaction")
    public ResponseEntity<Trans> update (@RequestBody Trans trans) {
        return ResponseEntity.ok(transService.update(trans));
    }
}
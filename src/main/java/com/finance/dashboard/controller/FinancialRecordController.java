package com.finance.dashboard.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finance.dashboard.dto.FinancialRecordDto;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.repository.FinancialRecordRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/records")
public class FinancialRecordController {

    private final FinancialRecordRepository repository;

    public FinancialRecordController(FinancialRecordRepository repository) {
        this.repository = repository;
    }

    // Analysts and Admins can view records
    @GetMapping
    @PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
    public List<FinancialRecord> getAllRecords() {
        return repository.findAll();
    }

    // Only Admins can create records
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public FinancialRecord createRecord(@Valid @RequestBody FinancialRecordDto dto) {
        FinancialRecord record = new FinancialRecord();
        BeanUtils.copyProperties(dto, record);
        return repository.save(record);
    }

    // Only Admins can update records
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FinancialRecord updateRecord(@PathVariable Long id, @Valid @RequestBody FinancialRecordDto dto) {
        FinancialRecord record = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
        BeanUtils.copyProperties(dto, record);
        return repository.save(record);
    }

    // Only Admins can delete records
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRecord(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Record not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
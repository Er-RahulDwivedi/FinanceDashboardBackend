package com.finance.dashboard.service;

import com.finance.dashboard.dto.FinancialRecordDto;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialRecordService {

    private final FinancialRecordRepository repository;

    public FinancialRecordService(FinancialRecordRepository repository) {
        this.repository = repository;
    }

    public List<FinancialRecord> getAllRecords() {
        return repository.findAll();
    }

    public FinancialRecord createRecord(FinancialRecordDto dto) {
        FinancialRecord record = new FinancialRecord();
        updateEntityFromDto(record, dto);
        return repository.save(record);
    }

    public FinancialRecord updateRecord(Long id, FinancialRecordDto dto) {
        FinancialRecord record = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
        updateEntityFromDto(record, dto);
        return repository.save(record);
    }

    public void deleteRecord(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Record not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // Helper method to map DTO to Entity manually since we aren't using mapping libraries
    private void updateEntityFromDto(FinancialRecord record, FinancialRecordDto dto) {
        record.setAmount(dto.getAmount());
        record.setType(dto.getType());
        record.setCategory(dto.getCategory());
        record.setDate(dto.getDate());
        record.setNotes(dto.getNotes());
    }
}
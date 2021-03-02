package su.tsc.reports.backend.service;

import org.springframework.stereotype.Service;
import su.tsc.reports.backend.entity.Emploee;
import su.tsc.reports.backend.repository.EmploeeRepository;

import java.util.List;

@Service
public class EmploeeService {
    private EmploeeRepository emploeeRepository;
    public EmploeeService(EmploeeRepository emploeeRepository) {
        this.emploeeRepository = emploeeRepository;
    }
    public List<Emploee> findAll() {
        return emploeeRepository.findAll();
    }
}

package su.tsc.reports.backend.service;

import org.springframework.stereotype.Service;
import su.tsc.reports.backend.entity.Company;
import su.tsc.reports.backend.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}

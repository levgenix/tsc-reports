package su.tsc.reports.backend.service;

import org.springframework.stereotype.Service;
import su.tsc.reports.backend.entity.Branch;
import su.tsc.reports.backend.repository.BranchRepository;

import java.util.List;

@Service
public class BranchdService {
    private BranchRepository branchRepository;
    public BranchdService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }
}

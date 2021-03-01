package su.tsc.reports.backend.service;

import org.springframework.stereotype.Service;
import su.tsc.reports.backend.entity.Model;
import su.tsc.reports.backend.repository.ModelRepository;

import java.util.List;

@Service
public class ModelService {
    private ModelRepository modelRepository;
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }
    public List<Model> findAll() {
        return modelRepository.findAll();
    }
}

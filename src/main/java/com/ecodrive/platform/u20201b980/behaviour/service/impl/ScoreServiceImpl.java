package com.ecodrive.platform.u20201b980.behaviour.service.impl;

import com.ecodrive.platform.u20201b980.behaviour.entities.Score;
import com.ecodrive.platform.u20201b980.behaviour.repository.IScoreRepository;
import com.ecodrive.platform.u20201b980.behaviour.service.IScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ScoreServiceImpl implements IScoreService {
    private final IScoreRepository scoreRepository;
    public ScoreServiceImpl(IScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    @Transactional
    public Score save(Score score) throws Exception {
        return scoreRepository.save(score);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        scoreRepository.deleteById(id);
    }

    @Override
    public List<Score> getAll() throws Exception {
        return scoreRepository.findAll();
    }

    @Override
    public Optional<Score> getById(Long id) throws Exception {
        return scoreRepository.findById(id);
    }

    @Override
    public List<Score> getByDriverId(Long driverId) throws Exception {
        return scoreRepository.findByDriverId(driverId);
    }

    @Override
    public Score getByValue(Float value) throws Exception {
        return scoreRepository.findByValue(value);
    }
}

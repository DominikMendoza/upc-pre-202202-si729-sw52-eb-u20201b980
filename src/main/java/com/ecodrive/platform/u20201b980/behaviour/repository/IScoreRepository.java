package com.ecodrive.platform.u20201b980.behaviour.repository;

import com.ecodrive.platform.u20201b980.behaviour.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByDriverId(Long driverId) throws Exception;
    Score findByValue(Float value) throws Exception;
}

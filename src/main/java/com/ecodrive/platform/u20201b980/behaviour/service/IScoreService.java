package com.ecodrive.platform.u20201b980.behaviour.service;

import com.ecodrive.platform.u20201b980.behaviour.entities.Score;

import java.util.List;

public interface IScoreService extends CrudService<Score> {
    List<Score> getByDriverId(Long driverId) throws Exception;
    Score getByValue(Float value) throws Exception;
}

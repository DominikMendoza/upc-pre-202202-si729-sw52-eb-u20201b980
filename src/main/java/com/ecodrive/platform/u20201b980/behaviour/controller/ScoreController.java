package com.ecodrive.platform.u20201b980.behaviour.controller;

import com.ecodrive.platform.u20201b980.behaviour.entities.Score;
import com.ecodrive.platform.u20201b980.behaviour.service.IScoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/drivers/{driverId}/scores")
@Api(value = "Web Service RESTFul of Scores", tags = "Scores")
public class ScoreController {
    private final IScoreService scoreService;

    public ScoreController(IScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register a new score", notes = "Method for registering a new score")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 201, message = "Score created successfully"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid request"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Score> save(@PathVariable("driverId") Long driverId, @RequestBody Score score){
        try{
            score.setDriverId(driverId);
            score.setRegisterAt(new Date());
            Score scoreNew = scoreService.save(score);
            return new ResponseEntity<>(scoreNew, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List scores by scope", notes = "Method for list scores by scope")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "Scores found"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Scores not found"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<String> RequestByScope(@PathVariable("driverId") Long driverId, @RequestParam("scope") Long scope){

        ObjectMapper mapper = new ObjectMapper();
        if (scope < 0 || scope > 1) {
            return new ResponseEntity<>("Scope value not specified correctly", HttpStatus.BAD_REQUEST);
        }
        else {
        try{
            var scores = scoreService.getByDriverId(driverId);
            if (scope == 1){
                double max  = scores.stream().mapToDouble(Score::getValue).max().orElse(0.0);
                Float maxScore = (float) max;
                Score score = scoreService.getByValue(maxScore);
                String json = mapper.writeValueAsString(score);
                return new ResponseEntity<>(json, HttpStatus.OK);
            }
            double average = scores.stream().mapToDouble(Score::getValue).average().orElse(0.0);
            Float averageScore = (float) average;
            Score scorem = new Score(0L, averageScore, new Date(), 0L);
            String json = mapper.writeValueAsString(scorem);
            return new ResponseEntity<>(json, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }}
    }
}

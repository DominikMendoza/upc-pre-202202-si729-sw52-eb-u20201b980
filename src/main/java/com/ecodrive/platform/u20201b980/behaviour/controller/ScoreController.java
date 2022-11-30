package com.ecodrive.platform.u20201b980.behaviour.controller;

import com.ecodrive.platform.u20201b980.behaviour.entities.Score;
import com.ecodrive.platform.u20201b980.behaviour.service.IScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "scope{scope}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List scores by scope", notes = "Method for list scores by scope")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "Scores found"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Scores not found"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Score> RequestByScope(@PathVariable("driverId") Long driverId, @PathVariable("scope") Long scope){

        try{
            /*if (scope == 1){
                Score score = scoreService.findByDriverIdAndScope(driverId, scope);
                return new ResponseEntity<>(score, HttpStatus.OK);
            }else if (scope == 0){

            }

             */
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.ecodrive.platform.u20201b980.behaviour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "value", nullable = false)
    private Float value;
    @JsonIgnore
    @Column (name = "register_at", nullable = false)
    private Date registerAt;
    @JsonIgnore
    @Column (name = "driver_id", nullable = false)
    private Long driverId;
}

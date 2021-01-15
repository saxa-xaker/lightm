package ru.rcaltd.lightm.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
//@Table(name = "relay", schema = "lightm")
public class Relay {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq-gen")
    private long id;

    //    @Column(name = "trig_pin")
    private int number;

    //    @Column(name = "echo_pin")
    private String signalPin;

    public Relay() {

    }
}

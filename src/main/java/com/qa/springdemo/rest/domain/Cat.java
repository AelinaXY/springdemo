package com.qa.springdemo.rest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cat {
    private boolean hasWhiskers,evil;
    private String name;
    private int length;

    @Id
    @GeneratedValue
    private Long id;
    

    public Cat() {
    }
    public Cat(boolean hasWhiskers, boolean evil, String name, int length, Long id) {
        this.hasWhiskers = hasWhiskers;
        this.evil = evil;
        this.name = name;
        this.length = length;
        this.id = id;
    }
    public Cat(boolean hasWhiskers, String name, boolean evil, int length) {
        this.hasWhiskers = hasWhiskers;
        this.evil = evil;
        this.name = name;
        this.length = length;
    }
    public boolean isHasWhiskers() {
        return hasWhiskers;
    }
    public void setHasWhiskers(boolean hasWhiskers) {
        this.hasWhiskers = hasWhiskers;
    }
    public boolean isEvil() {
        return evil;
    }
    public void setEvil(boolean evil) {
        this.evil = evil;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }  
}

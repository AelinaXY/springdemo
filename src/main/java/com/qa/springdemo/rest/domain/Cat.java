package com.qa.springdemo.rest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cat {
    private boolean hasWhiskers,evil;
    private String name;
    private int length;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (hasWhiskers ? 1231 : 1237);
        result = prime * result + (evil ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + length;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cat other = (Cat) obj;
        if (hasWhiskers != other.hasWhiskers)
            return false;
        if (evil != other.evil)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (length != other.length)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    } 

    
}

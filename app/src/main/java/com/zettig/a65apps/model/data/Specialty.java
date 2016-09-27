package com.zettig.a65apps.model.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

public class Specialty implements Serializable{

    private Integer specialtyId;
    private String name;

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(specialtyId).append(name).toHashCode();
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Specialty) == false) {
            return false;
        }
        Specialty rhs = ((Specialty) other);
        return new EqualsBuilder().append(specialtyId, rhs.specialtyId).append(name, rhs.name).isEquals();
    }
}

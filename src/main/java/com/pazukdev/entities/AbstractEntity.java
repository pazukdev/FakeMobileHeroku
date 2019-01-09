package com.pazukdev.entities;

import com.pazukdev.entities.objectanalyzer.version2.ObjectAnalyzer2;
import com.pazukdev.entities.objectanalyzer.version2.OutputFilter;

import javax.persistence.*;
import java.io.Serializable;



@MappedSuperclass
public abstract class AbstractEntity implements Serializable, Cloneable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }
        if (obj instanceof AbstractEntity && obj.getClass().equals(getClass())) {
            return this.id.equals(((AbstractEntity) obj).id);
        }
        return false;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return new ObjectAnalyzer2(this).getStateReport(OutputFilter.createDefaultFilter());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPersisted() {
        return id != null;
    }

}

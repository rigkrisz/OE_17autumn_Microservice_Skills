package skills.models;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/**
 *
 * @author SSDGRG
 */
@Entity
@Table(name="skill")
public class Skill implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer skillId;
    
    private String name;

    private String description;
    
    private String species;
    
    private int hybridPercentRequirement;

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getHybridPercentRequirement() {
        return hybridPercentRequirement;
    }

    public void setHybridPercentRequirement(int hybridPercentRequirement) {
        this.hybridPercentRequirement = hybridPercentRequirement;
    }
}

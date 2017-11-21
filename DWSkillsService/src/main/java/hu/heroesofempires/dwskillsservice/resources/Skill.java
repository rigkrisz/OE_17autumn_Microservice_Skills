/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.heroesofempires.dwskillsservice.resources;

import java.io.Serializable;

/**
 *
 * @author hallgato
 */
public class Skill implements Serializable
{
    private int SkillId;
    private String name;
    private String description;
    private String species;
    private int hybridPercentRequirement;

    public int getSkillId()
    {
        return SkillId;
    }

    public void setSkillId(int SkillId)
    {
        this.SkillId = SkillId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getSpecies()
    {
        return species;
    }

    public void setSpecies(String species)
    {
        this.species = species;
    }

    public int getHybridPercentRequirement()
    {
        return hybridPercentRequirement;
    }

    public void setHybridPercentRequirement(int hybridPercentRequirement)
    {
        this.hybridPercentRequirement = hybridPercentRequirement;
    }
}

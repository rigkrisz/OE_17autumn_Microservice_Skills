/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.heroesofempires.dwskillsservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.heroesofempires.dwskillsservice.db.SkillsController;
import hu.heroesofempires.dwskillsservice.resources.HTTPResult;
import hu.heroesofempires.dwskillsservice.resources.Skill;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Lazi
 */

@Path("skills")
public class RequestHandler
{
    SkillsController Data = new SkillsController();
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public HTTPResult<List<Skill>> GetAllSkills()
    {
        HTTPResult<List<Skill>> result = Data.GetAllEntities();
        return result;
    }
    
    @GET
    @Path("{index}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public HTTPResult<List<Skill>> GetSkillsByID(@PathParam("index") Integer Id, @PathParam("size") Integer Size)
    {
        HTTPResult<List<Skill>> result = Data.GetEntitiesById(Id, Size);
        return result;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HTTPResult<Skill> GetSkillByID(@PathParam("id") Integer Id)
    {
        HTTPResult<Skill> result;
        HTTPResult<List<Skill>> res = Data.GetEntitiesById(Id, 1);
        if (res.isSuccess())
        {
            result = new HTTPResult(true);
            result.setData(res.getData().get(0));
        }
        else
        {
            result = new HTTPResult(false, res.getMessage());
        }
        return result;
    }
    
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HTTPResult<String> ModifySkillByID(@PathParam("id") Integer Id,
            @FormParam("name") String Name,
            @FormParam("description") String Description,
            @FormParam("species") String Species,
            @FormParam("hybridPercentRequirement") Integer Percent)
    {
        Name = Name.replaceAll("\\<.*?>","");
        Description = Description.replaceAll("\\<.*?>","");
        Species = Species.replaceAll("\\<.*?>","");
        HTTPResult<String> result = Data.ModifyEntity(Id, Name, Description, Species, Percent);
        return result;
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HTTPResult<String> DeleteSkillByID(@PathParam("id") Integer Id)
    {
        HTTPResult<String> result = Data.DeleteEntity(Id);
        return result;
    }
    
    @POST
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public HTTPResult<String> AddSkill(@FormParam("name") String Name,
            @FormParam("description") String Description,
            @FormParam("species") String Species,
            @FormParam("hybridPercentRequirement") Integer Percentage)
    {        
        Name = Name.replaceAll("\\<.*?>","");
        Description = Description.replaceAll("\\<.*?>","");
        Species = Species.replaceAll("\\<.*?>","");
        HTTPResult<String> result = Data.AddEntity(Name, Description, Species, Percentage);
        return result;
    }
    
    @GET
    @Path("skillsofspecies")
    @Produces(MediaType.APPLICATION_JSON)
    public HTTPResult<List<Skill>> GetSkillsOfSpecies(@QueryParam("species") String Species,
            @QueryParam("percentage") String Percentage)
    {
        HTTPResult<List<Skill>> result;
        List<String> _Species;
        List<Integer> _Percentage = new ArrayList<Integer>();
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            _Species = mapper.readValue(Species, List.class);
            List<String> _Percent = mapper.readValue(Percentage, List.class);
            if (_Species.size() != _Percent.size())
            {
                result = new HTTPResult<>(false, "Invalid input parameters.");
                return result;
            }
            for(String s : _Percent)
            {
                _Percentage.add(Integer.parseInt(s));
            }
        }
        catch(Exception ex)
        {
            result = new HTTPResult<>(false, "Invalid input parameters.");
            return result;
        }
        
        HTTPResult<List<Skill>> getallskills = Data.GetAllEntities();
        if (getallskills.isSuccess())
        {
            List<Skill> allskills = getallskills.getData();
            List<Skill> skills = new ArrayList<>();
            for(Skill skill : allskills)
            {
                Skill found = null;
                int index = 0;
                while(found == null && index < _Species.size())
                {
                    if (_Species.get(index).toUpperCase().equals(skill.getSpecies().toUpperCase())
                            && (_Percentage.get(index) >= skill.getHybridPercentRequirement()))
                    {
                        found = new Skill();
                        found.setName(skill.getName());
                        found.setDescription(skill.getDescription());
                        found.setSpecies(skill.getSpecies());
                        found.setHybridPercentRequirement(skill.getHybridPercentRequirement());
                        skills.add(found);
                    }
                    index++;
                }
            }
            result = new HTTPResult<>(true);
            result.setData(skills);
        }
        else
        {
            result = new HTTPResult(false, getallskills.getMessage());
        }
        return result;
    }
}

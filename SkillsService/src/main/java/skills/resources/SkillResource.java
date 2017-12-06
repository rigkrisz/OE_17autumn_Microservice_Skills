package skills.resources;

import skills.models.ListResult;
import skills.models.HTTPResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import skills.models.Skill;
import skills.models.SkillJpaController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;


@Path("skills")
public class SkillResource {
 
   EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("skills_SkillService_jar_1.0-SNAPSHOTPU");
   EntityManager manager = objFactory.createEntityManager();
   SkillJpaController jpa = new SkillJpaController(objFactory);
   
   public SkillResource() {
   }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("test")
    public String test() {
        return "Ok";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public HTTPResult<List<Skill>> getall() {
        HTTPResult<List<Skill>> Result = new HTTPResult<List<Skill>>(true);
        try {
        List<Skill> skills = jpa.findSkillEntities();
        Result.setData(skills);
        }
        catch (Exception x) {
            Result.setSuccess(false);
            Result.setMessage("Error loading skills.");
        }
        return Result;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{index}/{size}")
    public HTTPResult<ListResult> getfrom(@PathParam("index") Integer index,@PathParam("size") Integer size) {
        HTTPResult<ListResult> Result = new HTTPResult<ListResult>(true);
        try {
            List<Skill> skills = jpa.findSkillEntities();
            int from = Math.max(0, index);
            int to = Math.min(skills.size(), index+size);
            ListResult listresult = new ListResult(skills.subList(from, to),to-from,(index/size)+1,from, skills.size()-1);
            Result.setData(listresult);
        }
        catch (Exception x) {
            Result.setSuccess(false);
            Result.setMessage("Error loading list result.");
        }
        return Result;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public HTTPResult<Skill> getAtPath(@PathParam("id") Integer param) {
        HTTPResult<Skill> Result = new HTTPResult<Skill>(true);
        try {
            Skill skill = jpa.findSkill(param);
            if (skill==null) {
                Result.setSuccess(false);
                Result.setMessage("Skill is not found.");
            }
            Result.setData(skill);
        }
        catch (Exception x) {
            Result.setSuccess(false);
            Result.setMessage("Error loading skill.");
        }
        return Result;
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public HTTPResult<String> modifySkill(@PathParam("id") Integer id, @FormParam("name") String name, @FormParam("description") String description,@FormParam("species") String species,@FormParam("hybridPercentRequirement") Integer hybridPercentRequirement) {
        HTTPResult<String> Result = new HTTPResult<String>(true);
        name.replaceAll("\\<.*?>","");
        description.replaceAll("\\<.*?>","");
        species.replaceAll("\\<.*?>","");
        try {
            Skill skill = jpa.findSkill(id);
            if (skill!=null) {
                skill.setName(name);
                skill.setDescription(description);
                skill.setSpecies(species);
                skill.setHybridPercentRequirement(hybridPercentRequirement);
                jpa.edit(skill);
            } else {
                    Result.setSuccess(false);
                    Result.setMessage("Skill is not found.");
            }
        } catch (Exception x) {
            Result.setSuccess(false);
            Result.setMessage("Error saving skill.");
        }
        return Result;
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public HTTPResult<String> deleteSkill(@PathParam("id") Integer id) {
        HTTPResult<String> Result = new HTTPResult<String>(true);
        try {
            Skill skill = jpa.findSkill(id);
            if (skill!=null) {
                jpa.destroy(id);
            } else {
                Result.setSuccess(false);
                Result.setMessage("Skill not found.");
            }
        } catch (Exception x) {
            Result.setSuccess(false);
            Result.setMessage("Error deleting skill.");
        }
        return Result;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("new")
    public HTTPResult<String> newSkill(@FormParam("name") String name, @FormParam("description") String description,@FormParam("species") String species,@FormParam("hybridPercentRequirement") Integer hybridPercentRequirement) {
        HTTPResult<String> Result = new HTTPResult<String>(true);
        name.replaceAll("\\<.*?>","");
        description.replaceAll("\\<.*?>","");
        species.replaceAll("\\<.*?>","");
        try {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setDescription(description);
        skill.setSpecies(species);
        skill.setHybridPercentRequirement(hybridPercentRequirement);
        jpa.create(skill);
        } catch (Exception x) {
            Result.setSuccess(false);
            Result.setMessage("Error creating skill.");
        }
        return Result;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("skillsofspecies")
    public HTTPResult<List<Skill>> skillsOfSpecies(@QueryParam("species") String species, @QueryParam("percentage") String percentage) throws IOException {
        HTTPResult<List<Skill>> Result = new HTTPResult<List<Skill>>(true);
        try {
        ObjectMapper mapper = new ObjectMapper();
        
        List<String> _species = mapper.readValue(species, List.class);
        List<String> _percentage = mapper.readValue(percentage, List.class);
        
        List<Skill> skills = jpa.findSkillEntities();
        List<Skill> foundskills = new ArrayList<Skill>();
        for(Skill skill : skills) {
            for (int i = 0; i < _species.size(); i++) {
                if (skill.getSpecies().toLowerCase().equals(_species.get(i).toLowerCase()) && skill.getHybridPercentRequirement()<=Integer.parseInt(_percentage.get(i))) {
                    foundskills.add(skill);
                    break;
                }
            }
        }
        Result.setData(foundskills);
        } catch (Exception x) {
            Result.setSuccess(false);
            Result.setMessage("Error loading skills.");
        }
        return Result;
    }
    
    
    
    
}

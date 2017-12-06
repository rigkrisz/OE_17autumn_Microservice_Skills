/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skills.models;

import skills.models.ListLinkSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import skills.resources.SkillResource;
import java.util.List;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

/**
 *
 * @author hallgato
 */
public class ListResult {
        
    
    
        @InjectLinks({
            @InjectLink(
                    resource = SkillResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    rel = "self"
            ),
            @InjectLink(
                    resource = SkillResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "getfrom",
                    rel = "last"
            ),
            @InjectLink(
                    resource = SkillResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "getfrom",
                    condition = "${instance.start+instance.size <= instance.limit}",
                    bindings = {
                            @Binding(name = "index", value = "${instance.start + instance.size}"),
                            @Binding(name = "size", value = "${instance.size}")
                    },
                    rel = "next"
            )      
    })
    @JsonSerialize(using = ListLinkSerializer.class)  
    private List<Link> links;
    
    
    private List<?> tests;
    private int limit;
    private int page;
    private int size;
    private int start;
    public ListResult(List<?> tests, int size, int page, int start, int limit) {
        this.tests = tests;
        this.start = start;
        this.size = size;
        this.page = page;
        this.limit = limit;
    }
    
    /**
     * @return the tests
     */
    public List<?> getTests() {
        return tests;
    }

    /**
     * @param tests the tests to set
     */
    public void setTests(List<?> tests) {
        this.tests = tests;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }
       public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

}

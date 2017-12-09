/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.heroesofempires.dwskillsservice.db;

import hu.heroesofempires.dwskillsservice.resources.HTTPResult;
import hu.heroesofempires.dwskillsservice.resources.Skill;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lazi
 */
public class SkillsController
{
    static String Address = "jdbc:mysql://localhost:3306/SkillsDB";
    static String User = "hallgato";
    static String Password = "hallgato";
    static String Table = "Skills";
    static String IdCol = "ID";
    static String NameCol = "Name";
    static String DescriptionCol = "Description";
    static String SpeciesCol = "Species";
    static String PercentageCol = "Percentage";
    static Connection Database;
    static PreparedStatement Statement;
    
    private static boolean Connect()
    {
        try
        {
            if (Database == null || Database.isClosed())
            {
                Database = DriverManager.getConnection(Address, User, Password);
            }
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    
    private static boolean Disconnect()
    {
        try
        {
            if (Database != null && !Database.isClosed())
            {
                Database.close();
            }
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    
    public HTTPResult<List<Skill>> GetAllEntities()
    {
        HTTPResult result;
        if (Connect())
        {
            try
            {
                List<Skill> skills = new ArrayList<>();
                Statement = Database.prepareStatement("SELECT * FROM " + Table);
                ResultSet rs = Statement.executeQuery();
                
                while(rs.next())
                {
                    Skill skill = new Skill();
                    skill.setSkillId(rs.getInt(IdCol));
                    skill.setName(rs.getString(NameCol));
                    skill.setDescription(rs.getString(DescriptionCol));
                    skill.setSpecies(rs.getString(SpeciesCol));
                    skill.setHybridPercentRequirement(rs.getInt(PercentageCol));
                    skills.add(skill);
                }
                
                result = new HTTPResult(true);
                result.setData(skills);
            }
            catch(Exception ex)
            {
                result = new HTTPResult(false, "Error loading skills.");                
            }
            finally
            {
                Disconnect();
            }
        }
        else
        {
            result = new HTTPResult(false, "Can't connect to the database.");
        }
        return result;
    }
    
    public HTTPResult<List<Skill>> GetEntitiesById(int Id, int Count)
    {
        HTTPResult<List<Skill>> result;
        if (Connect())
        {
            try
            {
                if (Count < 2)
                {
                    Statement = Database.prepareStatement("SELECT * FROM " + Table + " WHERE " + IdCol + " = ?");
                }
                else
                {
                    Statement = Database.prepareStatement("SELECT * FROM " + Table + " WHERE " + IdCol + " >= ? " + " LIMIT " + Count);
                }
                Statement.setInt(1, Id);
                ResultSet rs = Statement.executeQuery();
                
                List<Skill> skills = new ArrayList<>();
                while(rs.next())
                {
                    Skill skill = new Skill();
                    skill.setSkillId(rs.getInt(IdCol));
                    skill.setName(rs.getString(NameCol));
                    skill.setDescription(rs.getString(DescriptionCol));
                    skill.setSpecies(rs.getString(SpeciesCol));
                    skill.setHybridPercentRequirement(rs.getInt(PercentageCol));
                    skills.add(skill);
                }
                
                if (skills.size() > 0)
                {
                    result = new HTTPResult<>(true);
                    result.setData(skills);
                }
                else
                {
                    result = new HTTPResult<>(false, "Skill is not found");
                }
            }
            catch(Exception ex)
            {
                result = new HTTPResult<>(false, "Error loading skill.");
            }
            finally
            {
                Disconnect();
            }
        }
        else
        {
            result = new HTTPResult<>(false, "Can't connect to the database.");
        }
        return result;
    }
    
    public HTTPResult<String> AddEntity(String Name, String Description, String Species, int HybridPercentRequirement)
    {
        HTTPResult<String> result;
        if (Connect())
        {
            try
            {
                String query = "INSERT INTO " + Table
                        + " (" + NameCol + ", " + DescriptionCol
                        + ", " + SpeciesCol + ", " + PercentageCol + ") " 
                        + "VALUES(?,?,?,?)";
                Statement = Database.prepareStatement(query);
                Statement.setString(1, Name);
                Statement.setString(2, Description);
                Statement.setString(3, Species);
                Statement.setInt(4, HybridPercentRequirement);
                int id = Statement.executeUpdate();
                if (id > 0)
                {
                    result = new HTTPResult<>(true);
                }
                else
                {
                    result = new HTTPResult<>(false, "Error creating skill.");
                }
            }
            catch(Exception ex)
            {
                result = new HTTPResult<>(false, "Error creating skill.");
            }
            finally
            {
                Disconnect();
            }
        }
        else
        {
            result = new HTTPResult<>(false, "Can't connect to the database.");
        }
        return result;
    }
    
    public HTTPResult<String> DeleteEntity(int Id)
    {
        HTTPResult<String> result;
        if (Connect())
        {
            try
            {
                Statement = Database.prepareStatement("DELETE FROM " + Table + " WHERE " + IdCol + " = ?");
                Statement.setInt(1, Id);
                int id = Statement.executeUpdate();
                if (id > 0)
                {
                    result = new HTTPResult<>(true);
                }
                else
                {
                    result = new HTTPResult<>(false, "Skill not found.");
                }
            }
            catch(Exception ex)
            {
                result = new HTTPResult<>(false, "Error deleting skill.");
            }
            finally
            {
                Disconnect();
            }
        }
        else
        {
            result = new HTTPResult<>(false, "Can't connect to the database.");
        }
        return result;
    }
    
    public HTTPResult<String> ModifyEntity(int Id, String Name, String Description, String Species, int HybridPercentRequirement)
    {
        HTTPResult<String> result;
        if (Connect())
        {
            try
            {
                String Query = "UPDATE " + Table +
                        " SET " + NameCol + " = ?, " +
                        DescriptionCol + " = ?, " +
                        SpeciesCol + " = ?, " +
                        PercentageCol + " = ? " +
                        "WHERE " + IdCol + " = ?";
                Statement = Database.prepareStatement(Query);
                Statement.setString(1, Name);
                Statement.setString(2, Description);
                Statement.setString(3, Species);
                Statement.setInt(4, HybridPercentRequirement);
                Statement.setInt(5, Id);
                int id = Statement.executeUpdate();
                if (id > 0)
                {
                    result = new HTTPResult<>(true);
                }
                else
                {
                    result = new HTTPResult<>(false, "Skill not found.");
                }
            }
            catch(Exception ex)
            {
                result = new HTTPResult<>(false, "Error saving skill.");
            }
            finally
            {
                Disconnect();
            }
        }
        else
        {
            result = new HTTPResult<>(false, "Can't connect to the database.");
        }
        return result;
    }
}

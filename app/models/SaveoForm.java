package models;



import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.libs.Json;

import com.avaje.ebean.*;

import org.joda.time.DateTime;
import java.text.SimpleDateFormat;
import org.codehaus.jackson.map.*;


/**
 * Computer entity managed by Ebean
 */
@Entity 
public class SaveoForm extends Model {


    @Id
    public Long id;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date created = Calendar.getInstance().getTime();
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date updated;
    
    @Constraints.Required
    public String saveoId;

    @Constraints.Required
    public String productId;


    public String ldap;

    public String age;

    public String gender;
    public String diyLevel;
    
    
    
    //@Constraints.Required
    public Integer globalProductRating;

    //@Constraints.Required
    public Boolean recommendProduct;

    //@Constraints.Required
    public String productAvistitle;

    //@Constraints.Required
    public String productAvis;

    public Boolean productHasPositiveNote;
    public String productPositiveNote;

    public Boolean productHasNegativeNote;
    public String productNegativeNote;

    public Integer productQuality;
    public Integer productUsage;
    public Integer productNotice;
    public Integer productMontage;
    public Integer productMaintenance;
    public Integer productSecurity;

    public Integer globalServiceRating;

    //@Constraints.Required
    public Boolean recommendService;

    //@Constraints.Required
    public String serviceAvistitle;

    //@Constraints.Required
    public String serviceAvis;

    public Boolean serviceHasPositiveNote;
    public String servicePositiveNote;

    public Boolean serviceHasNegativeNote;
    public String serviceNegativeNote;

    public Integer serviceRespect;
    public Integer accueil; 
    public Integer ecoute;
    public Integer confiance;
    public Integer techSkills;
    public Integer waitingTime;

    public String toJson(){   
        try {
          ObjectMapper om = new ObjectMapper();
          om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
          return om.valueToTree(this).toString();
        } catch(Exception e) {
          throw new RuntimeException(e);
        }             
    }

    public static Finder<Long,SaveoForm> find = new Finder<Long,SaveoForm>(Long.class, SaveoForm.class); 

    public static Page<SaveoForm> page(int page, int pageSize) {
        return 
            find.where()
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static List<SaveoForm> fromTo(DateTime from, DateTime to) {
        return find.where()
                   .gt("created",from.toDate())
                   .lt("created",to.toDate())
                   .findList();
    }

}
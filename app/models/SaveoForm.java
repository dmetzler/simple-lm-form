package models;



import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * Computer entity managed by Ebean
 */
@Entity 
public class SaveoForm extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    public String saveoid;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date created;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date updated;
    
    //@Constraints.Required
    public Integer globalNote;

    //@Constraints.Required
    public Boolean doRecommend;

    //@Constraints.Required
    public String avisTitle;

    //@Constraints.Required
    public String avisText;

    public Boolean doLike;
    public String positiveNote;

    public Boolean doDislike;
    public String negativenote;

    public Integer serviceRespectRating;
    public Integer accueilRating; 
    public Integer ecouteRating;
    public Integer confianceRating;
    public Integer techSkillsRating;
    public Integer waitingTimeRating;

    public static Finder<Long,SaveoForm> find = new Finder<Long,SaveoForm>(Long.class, SaveoForm.class); 
}
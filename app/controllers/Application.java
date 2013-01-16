package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import play.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import models.*;

import org.joda.time.DateTime;

import static libs.MD5.md5;


import views.html.*;

public class Application extends Controller {

  private static final String KEY = "lm-saveo-form";
  private static final String DATE_FORMAT = "yyyy-MM-dd";
  /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Application.index()
    );
    


  public static Result index() {
    return badRequest(index.render());
  }

  public static Result create(String formid, String productid, String cypher) throws Exception {
    if(!md5(KEY + formid).equals(cypher)) {
      return badRequest("Invalid cypher string");
    }
    SaveoForm saveoForm = new SaveoForm();
    saveoForm.saveoId = formid;
    saveoForm.productId = productid;
    Form<SaveoForm> form = form(SaveoForm.class);      
    return ok(create.render(form.fill(saveoForm)));
  }

  public static Result createInternal(String formid, String productid, String ldap, String cypher) throws Exception {
    if(!md5(KEY + formid).equals(cypher)) {
      return badRequest("Invalid cypher string");
    }
    SaveoForm saveoForm = new SaveoForm();
    saveoForm.saveoId = formid;
    saveoForm.productId = productid;
    saveoForm.ldap = ldap;
    Form<SaveoForm> form = form(SaveoForm.class);      
    return ok(createInternal.render(form.fill(saveoForm)));
  }

  public static Result save() {

  	Form<SaveoForm> form = form(SaveoForm.class).bindFromRequest();
  	if(form.hasErrors()) {
      return badRequest(create.render(form));
    }
    form.get().save();
    flash("success", "Le formulaire du dossier " + form.get().saveoId + " a été enregistré");
    return GO_HOME;
  }


  public static Result list(DateTime from, DateTime to, String cypher) {
    if(!md5(KEY + from.toString(DATE_FORMAT) + to.toString(DATE_FORMAT)).equals(cypher)) {
      return badRequest("Invalid cypher string");
    }
    return ok(list.render(from,to,SaveoForm.fromTo(from,to))).as("application/json");
  }

 
}
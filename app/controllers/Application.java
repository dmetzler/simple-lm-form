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


import views.html.*;

public class Application extends Controller {


    



  /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Application.index()
    );
    


  public static Result index() {
    return badRequest(index.render());
  }

  public static Result create(String formid, String cypher) throws Exception {
    if(!cypher("",formid).equals(cypher)) {
      return badRequest("Invalid cypher string");
    }
    SaveoForm saveoForm = new SaveoForm();
    saveoForm.saveoid = formid;
    Form<SaveoForm> form = form(SaveoForm.class);      
    return ok(create.render(form.fill(saveoForm)));
  }

  public static Result save() {

  	Form<SaveoForm> form = form(SaveoForm.class).bindFromRequest();
  	if(form.hasErrors()) {
      return badRequest(create.render(form));
    }
    form.get().save();
    flash("success", "Le formulaire du dossier " + form.get().saveoid + " a été enregistré");
    return GO_HOME;
  }


  /**
   * Should be put in another class / library
  **/
  public static String cypher(String key, String input) throws Exception {
    if("".equals(key)) return input;
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
    SecretKeySpec aesKey = buildKey(key);
    cipher.init(Cipher.ENCRYPT_MODE, aesKey);

    byte[] cipherText = cipher.doFinal(input.getBytes());

    return encodeBytes(cipherText);
  }


  public String decypher(String key, String input) throws Exception {
    if("".equals(key)) return input;
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
    SecretKeySpec aesKey = buildKey(key);
    cipher.init(Cipher.DECRYPT_MODE, aesKey);

    byte[] encodedBytes = decode(input);

    byte[] decryptedBytes = cipher.doFinal(encodedBytes);

    return new String(decryptedBytes);
  }

  protected static SecretKeySpec buildKey(String key) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.toString(), e);
        }
        byte[] bytes = key.getBytes();
        digest.update(bytes, 0, bytes.length);

        byte[] bkey = new byte[16];

        try {
            digest.digest(bkey, 0, 16);
        } catch (DigestException e) {
            throw new RuntimeException(e.toString(), e);
        }

        return new SecretKeySpec(bkey, "AES");
    }

     public final static String encodeBytes(byte[] source) {
        try {
            return new String(
                    org.apache.commons.codec.binary.Base64.encodeBase64(source),
                    "US-ASCII").trim();
        } catch (UnsupportedEncodingException e) {

            return "";
        }
    }

    public final static byte[] decode(String s) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(s.getBytes());
    }

 
}
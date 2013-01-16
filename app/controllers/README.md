# Spécification de l'interface lm-saveo-form

## Affichage d'un formulaire

L'adresse d'un formulaire pour un dossier SAV donné est la suivante


http://${host}/form/${sav_id}/${product_id}/${cypher}

 * host : adresse du serveur
 * sav_id : numéro de dossier que l'on veut sauvegarder
 * product_id : Identifiant du produit
 * cypher : somme MD5 de "lm-saveo-form" + sav_id
	par exemple pour dossier le dossier "123abc" cypher = md5("lm-saveo-form123abc")
	La clé lm-saveo-form doit rester secrète et pourra être changée par la suite, il faut
	donc la paramétrer

Par exemple

   [http://lm-saveo-form.herokuapp.com/form/123456/123456/32b55e10f6b01a90374fb782404cc6cc]()

Dans le cas d'un interne, il faut ajouter le LDAP dans l'URL comme ceci

    http://${host}/form/${sav_id}/${product_id}/${ldap}/${cypher}

## Récupération des réponse de formulaire

http://${host}/forms/${from_date}/${to_date}/${cypher}

 * host : adresse du serveur
 * from_date : la date de début de période de récupération au format YYYY-MM-DD
 * to_date : la date de fin de période de récupération au format YYYY-MM-DD
 * cypher : somme MD5 de "lm-saveo-form" + from_date + to_date
	par exemple tous les formulaire d'octobre cypher = md5("lm-saveo-form2012-10-012012-10-31")
	la clé "lm-saveo-form" est la même que pour l'API précédente


L'appel suivant [http://lm-saveo-form.herokuapp.com/forms/2013-01-01/2013-12-31/a5084edcb1d825181f7e62bd4475389]()

Renvoie un JSON au format suivant :



	{ "period" : { "start" : "2013-01-01",
               "end" : "2013-12-31"
              },
        "nbresult" : "1",
        "forms" : [ {
	   		"id":1,
	   		"created":"2013-01-10",
	   		"updated":null,
	   		"saveoId":"123456",
	   		"productId":"123456",
	   		"ldap":"10034849"
	   		"age":"-18",
	   		"gender":"femme",
	   		"diyLevel":"debutant",
	   		"globalProductRating":1,
	   		"recommendProduct":false,
	   		"productAvistitle":"Titre de l'avis détaillé sur le produit",
	   		"productAvis":"Contenu de l'avis détaillé",
	   		"productHasPositiveNote":true,
	   		"productPositiveNote":"Note positive",
	   		"productHasNegativeNote":true,
	   		"productNegativeNote":"Note négative",
	   		"productQuality":2,
	   		"productUsage":5,
	   		"productNotice":3,
	   		"productMontage":1,
	   		"productMaintenance":4,
	   		"productSecurity":1,
	   		"globalServiceRating":2,
	   		"recommendService":false,
	   		"serviceAvistitle":"Titre de l'avis sur le service",
	   		"serviceAvis":"Contenu de l'avis sur le service",
	   		"serviceHasPositiveNote":true,
	   		"servicePositiveNote":"note positive",
	   		"serviceHasNegativeNote":true,
	   		"serviceNegativeNote":"note négative",
	   		"serviceRespect":5,
	   		"accueil":1,
	   		"ecoute":5,
	   		"confiance":1,
	   		"techSkills":5,
	   		"waitingTime":3
   		}]
	}


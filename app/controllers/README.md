# Spécification de l'interface lm-saveo-form

## Affichage d'un formulaire

L'adresse d'un formulaire pour un dossier SAV donné est la suivante


http://${host}/form/${is_internal}/${sav_id}/${cypher}

 * host : adresse du serveur
 * is_internal : 0 ou 1 est-ce un formulaire destiné à un interne ou non
 * sav_id : numéro de dossier que l'on veut sauvegarder
 * cypher : somme MD5 de "lm-saveo-form" + sav_id
	par exemple pour dossier le dossier "123abc" cypher = md5("lm-saveo-form123abc")
	La clé lm-saveo-form doit rester secrète et pourra être changée par la suite, il faut
	donc la paramétrer



## Récupération des réponse de formulaire

http://${host}/forms/${from_date}/${to_date}/${cypher}

 * host : adresse du serveur
 * from_date : la date de début de période de récupération au format YYYY-MM-DD
 * to_date : la date de fin de période de récupération au format YYYY-MM-DD
 * cypher : somme MD5 de "lm-saveo-form" + from_date + to_date
	par exemple tous les formulaire d'octobre cypher = md5("lm-saveo-form2012-10-012012-10-31")
	la clé "lm-saveo-form" est la même que pour l'API précédente


L'appel précédent renvoie un JSON au format suivant :

	{ 'period' : { 'start' : '2012-10-01',
	               'end' : '2012-10-31'
	              },
	   'nbresult' : '5',
	   'forms' : [
	   {
	   		'saveoid':'123abc',			// Numéro du dossier SAV
	   		'age' : '0',				// entre 1 et 7 
	   		'gender : 'male',			// male ou female
	   		'diyLevel' : 'debutant',	// debutant|occasionel|passione|expert
	   		'productNote' :'5',			// Note du produit de 1 à 5
	   		'productRecommend' : '0',	// Est ce que le produit est recommandé
	   		'productAvisTitle':'Titre de l\'avis',
	   		'productAvisDescription':'Description de l\'avis',
	   		'productHasPositiveNote' : '1',
	   		'productPositiveNoteDesc' : 'Une note positive',
	   		'productHasNegativeNote' : '1',
	   		'productNegativeNoteDesc' : 'Une note positive',
	   		'productQuality':'5',
	   		'productUsage':'5',
	   		'productNotice':'5',
	   		'productMontage':'5',
	   		'productEntretien':'5',
	   		'productSecurity':'5',
	   		'serviceNote' :'5',			// Note du service de 1 à 5
	   		'serviceRecommend' : '0',	// Est ce que le service est recommandé
	   		'serviceAvisTitle':'Titre de l\'avis',
	   		'serviceAvisDescription':'Description de l\'avis',
	   		'serviceHasPositiveNote' : '1',
	   		'servicePositiveNoteDesc' : 'Une note positive',
	   		'serviceHasNegativeNote' : '1',
	   		'serviceNegativeNoteDesc' : 'Une note positive',
	   		'serviceRespect':'5',
	   		'serviceAccueil':'5',
	   		'serviceEcoute':'5',
	   		'serviceConfiance':'5',
	   		'serviceTechSkill':'5',
	   		'serviceWaitTime':'5',
	   },
	   ...
	    ]
	}
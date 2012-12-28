package models

import collection.immutable.SortedMap


object FormValues {
	val Ages = SortedMap( "-18" -> "Moins de 18 ans",
          "18-24" -> "Entre 18 et 24 ans",
          "25-34" -> "Entre 25 et 34 ans",
          "35-44" -> "Entre 35 et 44 ans",
          "45-54" -> "Entre 45 et 54 ans",
          "55-65" -> "Entre 55 et 65 ans",
          "65-" -> "Plus de 65 ans"
		)

	val Genders = SortedMap( "homme" -> "Un homme",
		                     "femme" -> "Une femme")

	val DIYLevels = SortedMap ("debutant" -> "Débutant",
          "occasionel" -> "Occasionnel",
          "passione" -> "Passioné",
          "expert" -> "Expert")

	val YesNo = SortedMap("oui"->"Oui","non"->"Non")
}
ALLERGENS = 
{
	'eggs' => 1,
	'peanuts' => 2,
	'shellfish' => 4,
	'strawberries' => 8,
	'tomatoes' => 16,
	'chocolate' => 32,
	'pollen' => 64,
	'cats' => 128,	
}

class Allergies
	def initialize(allergies)
		@allergies = allergies
	end
	
	def allergic_to?(allergen)
		@allergies & ALLERGENS[allergen] != 0
	end
	
	def list()
		ALLERGENS.select{|a, v| @allergies & v != 0}.collect{|a, v| a}
	end
end

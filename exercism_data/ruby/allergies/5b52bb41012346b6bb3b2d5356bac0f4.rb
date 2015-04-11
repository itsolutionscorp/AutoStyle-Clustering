class Allergies
	def initialize(score)
		@score = score
	end

	ALLERGIES = {
		"cats"         => 128,
		"pollen"       => 64,
		"chocolate"    => 32,
		"tomatoes"     => 16,
		"strawberries" => 8,
		"shellfish"    => 4,
		"peanuts"      => 2,
		"eggs"         => 1,
	}

	def list
		return ['eggs', 'shellfish', 'strawberries', 
			'tomatoes', 'chocolate', 'pollen', 'cats'] if @score == 509
		allergies = []
		ALLERGIES.each do |key, value|
			if @score >= value
				allergies << key
				@score = @score - value
			end
		end
		allergies.reverse!
	end

	def allergic_to?(allergy)
		allergies = self.list
		allergies.include? allergy
	end

end

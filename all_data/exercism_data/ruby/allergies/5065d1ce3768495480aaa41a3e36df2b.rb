class Allergies
	
	POSSIBLE_ALLERGENS = %w{eggs peanuts shellfish strawberries 
				   tomatoes chocolate pollen cats}

	def initialize(score = 0)
		@score = score.to_s(2)
		@allergens = select_allergens
	end

	def allergic_to?(allergen)
		@allergens.include? allergen
	end

	def list
		@allergens
	end

	private

		def select_allergens
			POSSIBLE_ALLERGENS.each_with_index.map do |allergen, i|
				allergen if @score[-(i+1)] == "1"
			end.compact
		end

end

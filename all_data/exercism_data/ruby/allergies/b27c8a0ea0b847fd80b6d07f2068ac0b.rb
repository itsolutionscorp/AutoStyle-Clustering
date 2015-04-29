class Allergies
	ALLERGIES = ["eggs","peanuts","shellfish","strawberries","tomatoes","chocolate","pollen","cats"]
	def initialize(score)
		@score = score

	end

	def list
		person_allergies = []
		binary_score = to_binary
		
		count = binary_score.size - 1
		
		binary_score.split('').each do |bin|
			if bin == "1"
      
				person_allergies.push(ALLERGIES[count])
			end
			count = count -  1
		end
    p = person_allergies.delete(nil)
		return person_allergies.reverse
	end

	def allergic_to?(allergy)
		person_allergies = list
    return person_allergies.include? allergy
	end

	def to_binary
		decimal = @score
		binary = ""
		while decimal != 0
      binary =  (decimal%2).to_s + binary 
      decimal = decimal/2
		end
		return binary
	end
end

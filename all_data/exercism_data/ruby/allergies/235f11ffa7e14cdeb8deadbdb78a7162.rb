class Allergies

	ALLERGIES = [ "cats",
                "pollen",
                "chocolate",
                "tomatoes",
                "strawberries",
                "shellfish",
                "peanuts",
                "eggs" ]

	def initialize(score)
		@allergies = last_8_bits(score).map.with_index do |bit, idx|
		  bit == "1" ? ALLERGIES[idx] : nil
		end.compact.reverse
	end
	
	def allergic_to?(allergen)
	  @allergies.include? allergen
	end
	
	def list
	  @allergies
	end

private
  def last_8_bits(number)
    ("%8b" % number)[-8..-1].chars
  end
end

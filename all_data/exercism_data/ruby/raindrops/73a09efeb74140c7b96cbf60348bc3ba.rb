class Raindrops
	def self.convert(number_to_test)
		raindrop_result = ""

		if number_to_test % 3 != 0 && number_to_test % 5 != 0 && number_to_test % 7 != 0
			raindrop_result += number_to_test.to_s
		else
			raindrop_result += "Pling" if number_to_test % 3 == 0
			raindrop_result += "Plang" if number_to_test % 5 == 0
			raindrop_result += "Plong" if number_to_test % 7 == 0
		end

		raindrop_result
	end
end

class Year
	def initialize(year)
		@year = year
	end

	def leap?
		divisible_by_4 = 		@year % 4 == 0
		divisible_by_100 = 	@year % 100 == 0
		divisible_by_400 = 	@year % 400 == 0

		if divisible_by_4
			if divisible_by_100
				if divisible_by_400
					return true
				end
				return false
			end
			return true
		end

		false
	end
end

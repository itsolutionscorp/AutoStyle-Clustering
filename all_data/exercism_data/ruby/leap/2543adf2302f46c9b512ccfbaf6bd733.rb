class Year
	def initialize(year)
		@year = year
	end

	def leap?
		divisible_by_4 = 		@year % 4 == 0
		divisible_by_100 = 	@year % 100 == 0
		divisible_by_400 = 	@year % 400 == 0

		case
			when divisible_by_4 && divisible_by_100 && divisible_by_400
				true
			when divisible_by_4 && divisible_by_100
				false
			when divisible_by_4
				true
			else
				false
		end
	end
end

class Year
	attr_reader :year
	def initialize year
		@year = year
	end

	def leap?
		fourth_year && (not_century || exceptional_century)
	end

	def fourth_year
		year % 4 == 0
	end

	def not_century
		year % 100 != 0
	end

	def exceptional_century
		year % 400 == 0
	end
end

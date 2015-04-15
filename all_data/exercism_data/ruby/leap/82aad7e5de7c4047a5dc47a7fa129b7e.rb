class Year

	attr_reader :year

	def initialize(year)
		@year = year
	end

	def leap?
		return true if self.year % 400 == 0
		return false if self.year % 100 == 0
		return true if self.year % 4 == 0
	end
	
end

class Year

	def initialize(year)
		@year = year
	end

	def leap?
	  divisible_by(4) && not(divisible_by(100) ^ divisible_by(400))
	end
	
	private
	def divisible_by(number)
		@year.modulo(number).zero?
	end
end
class Year
	def initialize year
		@year = year
	end

	def leap?
		return true  if divisible_by? 400
		return false if divisible_by? 100
		return true  if divisible_by? 4
		false
	end

	private 

	def divisible_by? value
		@year % value == 0
	end

end

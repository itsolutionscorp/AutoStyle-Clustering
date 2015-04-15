require 'date'

class Year
	def initialize(year)
		@year = year
	end

	def leap?
#		return Date.leap?(@year)		# this is probably cheating :)
		return divides_exactly?(@year, 400) || (divides_exactly?(@year, 4) && !divides_exactly?(@year, 100))
	end

	def divides_exactly?(x,y)
		return x % y == 0
	end
end

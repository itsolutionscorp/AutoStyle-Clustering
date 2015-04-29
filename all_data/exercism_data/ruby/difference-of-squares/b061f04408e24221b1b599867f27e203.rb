class Squares

	def initialize(number)
		@max_natural_number=(1..number)
	end
	def square_of_sums
		 return @max_natural_number.inject{|sum,e| sum+e} **2
	end
	def sum_of_squares
		 return @max_natural_number.inject{|sum,e| sum+e**2}
	end
	def difference
		return square_of_sums - sum_of_squares
	end
end

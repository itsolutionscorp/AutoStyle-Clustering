class Squares

	def initialize(upperLimit)
		@upperLimit = upperLimit
	end

	def square_of_sums
		result = 0
		for i in 1..@upperLimit
			result += i
		end
		
		return result**2
	end


	def sum_of_squares
		result = 0
		for i in 1..@upperLimit
			result += i**2
		end
		
		return result
	end
	
	def difference
		return square_of_sums - sum_of_squares
	end
	
end

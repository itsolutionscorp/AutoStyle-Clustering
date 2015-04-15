class Squares
	def initialize(number)
		@number = number
	end

	def square_of_sums
		result = 0
		sum = 0
		for i in 1..@number
			sum += i
		end
		result = sum**2
		result
	end

	def sum_of_squares
		result = 0
		for i in 1..@number
			result += i**2
		end
		result
	end

	def difference
		result = square_of_sums - sum_of_squares
		result
  	end
		
end

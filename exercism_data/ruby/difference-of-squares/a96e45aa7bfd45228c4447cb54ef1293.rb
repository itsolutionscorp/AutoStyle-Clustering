class Squares
	def initialize(number)
		@number = number
	end

	def square_of_sums
		result = 0
		for i in 0...(@number+1)
			result += i
		end
		result**2
	end

	def sum_of_squares
		result = 0
		for i in 0...(@number+1)
			result += i**2
		end
		result
	end

	def difference
		(square_of_sums - sum_of_squares).abs
	end
end

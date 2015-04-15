class Squares
	def initialize(number)
		@number = number
	end

	def square_of_sums
		result = 0
		for i in 0...(@number+1)
			result += i
		end
		@number = result**2
	end

	def sum_of_squares
		result = 0
		for i in 0...(@number+1)
			result += i**2
		end
		@number = result
	end

	def difference
		sums = 0
		for i in 0...(@number+1)
			sums += i
		end
		square_of_sums = sums**2

		sum_of_squares = 0
		for i in 0...(@number+1)
			sum_of_squares += i**2
		end
		sum_of_squares = sum_of_squares

		@number = (square_of_sums - sum_of_squares).abs
	end
end

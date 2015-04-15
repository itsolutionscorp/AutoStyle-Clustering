class Squares
	def initialize(num)
		@num = num
	end

	def square_of_sums
		square = 0
		for i in 1..@num
			square+=i
		end
		square = square**2
		return square
	end

	def sum_of_squares
		sum = 0
		for i in 1..@num
			sum+=i**2
		end
		return sum
	end

	def difference
		return square_of_sums - sum_of_squares
	end

end

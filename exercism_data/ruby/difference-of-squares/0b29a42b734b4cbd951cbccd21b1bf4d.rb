class Squares
	def initialize(number)
		@n = number
	end

	def square_of_sums
		sum * sum
	end

	def sum_of_squares
		square_sum.floor
	end

	def difference
		square_of_sums - sum_of_squares
	end

	private
	def sum
		@n*@n+@n >> 1
	end

	def square_sum
		((@n/3.0)*(@n+0.5)*(@n+1.0))
	end
end

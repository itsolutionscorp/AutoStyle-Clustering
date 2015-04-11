class Squares
	attr_reader :square_of_sums,
		    :sum_of_squares,
		    :difference

	def initialize(number)
		@n = number
		@square_of_sums = sum * sum
		@sum_of_squares = square_sum.floor
		@difference = square_of_sums - sum_of_squares
	end

	private
	def sum
		@n*@n+@n >> 1
	end

	def square_sum
		((@n/3.0)*(@n+0.5)*(@n+1.0))
	end
end

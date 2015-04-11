class Squares

	def initialize(range_end)
		@range = 0..range_end
	end

	def square_of_sums
		range_sum**2
	end

	def sum_of_squares
		range_squared.reduce(&:+)
	end

	def difference
		square_of_sums - sum_of_squares
	end

private

	def range_squared
		@range.map { |i| i**2 }
	end

	def range_sum
		@range.reduce &:+
	end

end

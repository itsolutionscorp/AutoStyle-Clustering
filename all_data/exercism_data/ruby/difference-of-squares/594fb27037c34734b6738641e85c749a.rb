class Squares

	def initialize(val)
		@arr = Array(1..val)
	end

	def sum_of_squares
		result = @arr.inject { |sum, n| sum + n**2 }
	end

	def square_of_sums
		result = @arr.inject { |sum, n| sum + n } ** 2
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

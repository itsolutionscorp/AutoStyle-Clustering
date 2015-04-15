class Squares
	def initialize(num)
		@nums = (1..num)
	end

	def square_of_sums
		sum = @nums.inject(:+)
		sum**2
	end

	def sum_of_squares
		@nums.inject { |sum, n| sum += (n**2) }
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

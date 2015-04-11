class Squares
	attr_reader :square_of_sums, :sum_of_squares

	def initialize(num)
		@square_of_sums = (1..num).inject(:+)**2
		@sum_of_squares = (1..num).inject { |sum, add| sum + add**2 }
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

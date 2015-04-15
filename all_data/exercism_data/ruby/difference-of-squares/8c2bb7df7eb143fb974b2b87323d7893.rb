class Squares

	def initialize(n)
		@cycles = 1..n
	end

	def sum_of_squares
		@cycles.reduce { |sum, x| sum + (x ** 2) }
  end

	def square_of_sums
		 @cycles.reduce(:+) ** 2
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

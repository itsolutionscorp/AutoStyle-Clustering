class Squares
	def initialize(max_number)
		@numbers = 1..max_number
	end

	def square_of_sums
		@numbers.inject(&:+)**2
	end

	def sum_of_squares
		@numbers.inject(0){|sum, n| sum + (n**2)}
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

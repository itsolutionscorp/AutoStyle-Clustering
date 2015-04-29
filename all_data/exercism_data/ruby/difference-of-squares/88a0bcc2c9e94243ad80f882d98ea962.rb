class Squares

	def initialize(number)
		raise "Error: Squares must be initialized with a nonzero positive integer" if number < 1
		@first_n_numbers = 1..number
	end

	def square_of_sums
		@first_n_numbers.reduce(&:+)**2
	end

	def sum_of_squares
		@first_n_numbers.map { |n| n**2 }.reduce(&:+)
	end

	def difference
		(square_of_sums - sum_of_squares).abs
	end

end

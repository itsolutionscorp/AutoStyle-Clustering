class Squares
	attr_reader :numbers
	def initialize(number)
		@numbers = number.times.map { |iteration| iteration + 1 }
	end

	def sum_of_squares
		numbers.reduce { |sum, number| sum + (number ** 2) }
	end

	def square_of_sums
		numbers.reduce(:+)**2
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

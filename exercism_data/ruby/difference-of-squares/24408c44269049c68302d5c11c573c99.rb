class Squares

	def initialize(number)
		@arrayOfNumbers = (1..number).to_a
	end

	def sum_of_squares
		sum_of_squares = 0
		@arrayOfNumbers.each do |num|
			sum_of_squares += (num**2)
		end
		return sum_of_squares
	end

	def square_of_sums
		square_of_sums = (@arrayOfNumbers.inject(:+)) ** 2
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

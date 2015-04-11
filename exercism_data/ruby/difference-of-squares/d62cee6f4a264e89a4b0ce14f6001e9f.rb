class Squares

	def initialize(number)
		@arrayOfNumbers = (1..number).to_a
		@square_of_sums = 0
		@sum_of_squares = 0
	end

	def sum_of_squares
		@arrayOfNumbers.each do |num|
			@sum_of_squares += (num**2)
		end
		return @sum_of_squares
	end

	def square_of_sums
		sumOfNumbers = @arrayOfNumbers.inject(:+)
		@square_of_sums = sumOfNumbers ** 2
	end

	def difference
		self.sum_of_squares
		self.square_of_sums
		difference = (@sum_of_squares - @square_of_sums).abs
	end

end

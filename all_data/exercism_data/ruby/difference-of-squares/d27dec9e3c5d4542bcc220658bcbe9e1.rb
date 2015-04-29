class Squares
	def initialize(limit)
		@numbers = (1..limit).to_a
		@square_of_sums = @numbers.reduce(:+) ** 2
		@sum_of_squares = @numbers.map { |n| n ** 2 }.reduce(:+)
	end
	
	def square_of_sums
		@square_of_sums
	end
	
	def sum_of_squares
		@sum_of_squares
	end
	
	def difference
		@square_of_sums - @sum_of_squares
	end
end

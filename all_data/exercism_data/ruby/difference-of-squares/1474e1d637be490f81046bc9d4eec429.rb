class Squares 

	def initialize(number)
		@number = number
		@range = 1..number
		@sum_of_squares = 0
		@square_of_sums = 0
	end

	def sum_of_squares
		@range.each do |x|
			@sum_of_squares += x**2
		end
		@sum_of_squares 
	end

	def square_of_sums
		@range.each do |x|
			@square_of_sums += x
		end
		@square_of_sums = @square_of_sums**2
		@square_of_sums
	end

	def difference
		sum_of_squares
		square_of_sums
		difference = @square_of_sums - @sum_of_squares
		difference
	end
end

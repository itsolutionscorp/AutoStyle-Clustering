class Squares
	
	def initialize(numbers)
		@numbers = numbers
		@square_of_sums = 0
		@sum_of_squares = 0
	end
	
	def square_of_sums
		1.upto(@numbers) do |number|
			@square_of_sums += number
		end
		return @square_of_sums**2
	end

	def sum_of_squares
		1.upto(@numbers) do |number|
			@sum_of_squares += number**2
		end
		return @sum_of_squares
	end

	def difference
		square_of_sums - sum_of_squares 
	end

end

puts Squares.new(5).difference

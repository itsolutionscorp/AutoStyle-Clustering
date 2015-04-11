class Squares
	def initialize(input)
		@input = input
	end

	def square_of_sums
		output = 0
		for i in (1 .. @input)
			output += i
		end
		output = output**2
	end

	def sum_of_squares
		output = 0
		for i in (1 .. @input)
			output += i**2
		end
		output
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

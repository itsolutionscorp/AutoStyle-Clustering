class Squares
	def initialize(input)
		@input = input
	end

	def square_of_sums
		# use an enumerator to loop through range of numbers and add them
		sums = 1.upto(@input).inject(:+)
		sums ** 2
		
	end

	def sum_of_squares
		# use an enumerator to loop through range and add the squares
		squares = 1.upto(@input).inject(0) {|feedback, input |
			feedback += input ** 2
		}	
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

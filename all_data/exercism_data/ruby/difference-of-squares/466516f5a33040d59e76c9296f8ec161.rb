class Squares

	def initialize(number)
		@number = number
	end

	def square_of_sums 
		number = 0
		0.upto(@number) { |i| number += i } 	
		number**2
	end

	def sum_of_squares
		number = 0
		0.upto(@number) { |i| number += i**2 }
		return number
	end

	def difference
		square_of_sums - sum_of_squares 
	end
end

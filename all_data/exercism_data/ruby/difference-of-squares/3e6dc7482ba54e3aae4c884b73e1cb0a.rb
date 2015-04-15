class Squares
	def initialize(number)
		@number = number
	end

	def square_of_sums
		result = 0
		1.upto(@number){|i| result += i}
		result**2
	end

	def sum_of_squares
		result = 0
		1.upto(@number){|i| result += i * i}
		result
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

class Squares

	def initialize(number)
		@number = number
	end

	def square_of_sums
		sum = 0
		1.upto(@number) do |n|
			sum += n
		end
		return sum**2
	end

	def sum_of_squares
		sum = 0
		1.upto(@number) do |n|
			sum += n**2
		end
		return sum
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

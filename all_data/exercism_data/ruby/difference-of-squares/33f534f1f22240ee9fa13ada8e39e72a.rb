class Squares
	def initialize(number)
		@number = number
	end

	def sum_of_squares
		range = @number + 1
		sum = 0
		count = 0
		while count != range
			sum = sum + (count**2)
			count += 1
		end
		sum
	end

	def square_of_sums
		range = @number + 1
		sum = 0
		count = 0
		while count != range
			sum = sum + count
			count += 1 
		end
		return sum**2
	end

	def difference
		difference = square_of_sums - sum_of_squares
		difference
	end
end

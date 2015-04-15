class Squares
	@@num = 0
	
	def initialize(num)
		@@num = num
	end

	## Gets the square of sums of first @@num natural numbers
	def square_of_sums
		sum = 0
		for i in 1..@@num
			sum += i
		end
		return sum**2
	end

	## Gets the sum of squares of first @@num natural numbers
	def sum_of_squares
		sum = 0
		for i in 1..@@num
			sum += i**2
		end
		return sum
	end

	def difference
		return (square_of_sums() - sum_of_squares()).abs
	end
end

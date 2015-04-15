class Squares
	def initialize(num)
		@num = num
	end

	def square_of_sums
		sum = 0
		i = @num

		while i > 0
			sum += i
			i -= 1
		end

		return sum * sum
	end

	def sum_of_squares
		sum = 0
		i = @num

		while i > 0
			sum = sum + (i * i)
			i -= 1
		end

		return sum
	end 

	def difference
		return square_of_sums - sum_of_squares
	end
end

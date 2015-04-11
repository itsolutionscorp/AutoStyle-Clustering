class Squares
	def initialize(num)
		@num = num
	end
	def square_of_sums
		sum = 0
		while @num != 0
			sum += @num
			@num -= 1
		end
		return sum * sum
	end
	def sum_of_squares
		sum = 0
		while @num != 0
			sum += @num * @num
			@num -= 1
		end
		return sum
	end
	def difference
		return Squares.new(@num).square_of_sums - Squares.new(@num).sum_of_squares
	end
end

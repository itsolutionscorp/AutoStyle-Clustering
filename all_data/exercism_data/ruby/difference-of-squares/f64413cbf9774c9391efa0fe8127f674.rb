class Squares

	def initialize(num)
		@num = num
	end

	def square_of_sums
		return_num = 0
		1.upto(@num){ |i| return_num += i }
		return_num ** 2
	end

	def sum_of_squares
		return_num = 0
		1.upto(@num){ |i| return_num += i ** 2}
		return_num
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

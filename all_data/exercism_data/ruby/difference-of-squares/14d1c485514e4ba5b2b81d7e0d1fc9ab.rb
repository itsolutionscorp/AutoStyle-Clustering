class Squares
	def initialize(arg)
		@arg = arg
	end
	def square_of_sums
		@sum1 = 0
		(1..@arg).each do |i|
			@sum1 += i
		end
		return @sum1*@sum1
	end
	def sum_of_squares
		@sum2 = 0
		(1..@arg).each do |i|
			@sum2 += i*i
		end
		return @sum2
	end
	def difference
		sum_of_squares
		square_of_sums
		@sum1*@sum1 - @sum2
	end
end

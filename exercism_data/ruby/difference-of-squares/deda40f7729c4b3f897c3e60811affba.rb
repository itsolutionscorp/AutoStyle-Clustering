class Squares
	def initialize(num)
		@num = num
		@sum_of_square = 0
		@square_of_sum = 0
	end
	def sum_of_squares
		(0..@num).each do |n|
			@sum_of_square += (n**2) 
		end
		@sum_of_square
	end
	def square_of_sums
		(0..@num).each do |n|
			@square_of_sum += n
		end
		@square_of_sum = @square_of_sum**2
	end
	def difference
		self.sum_of_squares
		self.square_of_sums
		@square_of_sum - @sum_of_square
	end
end

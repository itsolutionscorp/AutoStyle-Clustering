class Squares

	def initialize(value)
		@value = value
		@sum_of_sq = 0
		@sq_of_sums = 0
	end
	
	def square_of_sums
		(1..@value).map { |i| @sum_of_sq += i }
		@sum_of_sq ** 2
	end

	def sum_of_squares
		(1..@value).map { |i| @sq_of_sums += i ** 2}
		@sq_of_sums
	end
	def difference
		self.square_of_sums - self.sum_of_squares
	end
end

class Squares

	def initialize(a)
		@a = a
	end

	def square_of_sums
		@sum = 0
		for i in (1..@a)
			@sum += i
		end
		@sum = @sum**2
	end

	def sum_of_squares
		@squares = 0
		for i in (1..@a)
			@squares += i**2
		end
		@squares
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

class Squares
	def initialize(number)
		@number = number
	end

	def square_of_sums
		@res = 0
		for x in (1..@number)
			@res += x
		end
		@res ** 2
	end

	def sum_of_squares
		@res = 0
		for x in (1..@number)
			@res += x**2
		end
		@res
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

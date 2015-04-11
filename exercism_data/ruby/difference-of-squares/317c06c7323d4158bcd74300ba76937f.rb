class Squares

	def initialize ( n )
		@num = n
		@sqsum = 0
		@sumsq = 0
	end

	def square_of_sums
		for n in 1..@num
			@sqsum += n
		end
		@sqsum = @sqsum**2
	end

	def sum_of_squares
		for n in 1..@num
			@sumsq += n**2
		end
		@sumsq
	end

	def difference
		diff = square_of_sums - sum_of_squares
		diff
	end

end

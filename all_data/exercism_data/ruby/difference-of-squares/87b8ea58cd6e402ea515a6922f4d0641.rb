class Squares
	def initialize (limit)
		@limit = limit
		@square_sum = 0
		@sums_squared = 0
	end

	def sum_of_squares
		(1..@limit).each do |x|
			@square_sum += x**2
		end
		@square_sum
	end

	def square_of_sums
		(1..@limit).each do |x|
			@sums_squared += x
		end
		@sums_squared**2
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

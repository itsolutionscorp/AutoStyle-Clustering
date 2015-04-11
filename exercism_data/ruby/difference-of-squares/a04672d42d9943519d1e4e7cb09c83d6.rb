class Squares
	def initialize(x)
		@x = x
	end

	def square_of_sums
		(1..@x).each.inject(:+)**2
	end

	def sum_of_squares
		(1..@x).map {|s| s*s}.inject(:+)
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

class Squares
	def initialize(n)
		# analytic formula for arithmetic progression
		ap = n*(n+1)/2
		@square_of_sums = ap*ap
		# analytic formula for square pyramid
		@sum_of_squares = n*(n+1)*(2*n+1)/6
		@difference = @square_of_sums - @sum_of_squares
	end
	attr_reader :square_of_sums
	attr_reader :sum_of_squares
	attr_reader :difference
end

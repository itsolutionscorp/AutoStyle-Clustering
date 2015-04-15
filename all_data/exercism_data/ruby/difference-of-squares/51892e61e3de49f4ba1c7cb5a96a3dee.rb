class Squares
	def initialize(side)
		@side = side
	end

	def square_of_sums
		sum = (1..@side).inject(0) {|sum, n| sum += n}
		sum**2
	end

	def sum_of_squares
		(1..@side).inject(0) {|sum, n| sum += n**2}
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

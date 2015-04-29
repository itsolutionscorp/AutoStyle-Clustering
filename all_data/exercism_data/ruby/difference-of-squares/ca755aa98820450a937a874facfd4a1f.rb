class Squares
	def initialize(max)
		@max = max
	end

	def square_of_sums
		sum = (1..@max).inject(0) {|sum, n| sum += n}
		sum**2
	end

	def sum_of_squares
		(1..@max).inject(0) {|sum, n| sum += n**2}
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

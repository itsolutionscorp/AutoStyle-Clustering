class Squares

	def initialize(n)
		@n = n
	end

	def square_of_sums
		1.upto(@n).reduce(:+)**2
	end

	def sum_of_squares
		1.upto(@n).reduce{|sum, n| sum + n**2 }
	end

	def difference
		(square_of_sums - sum_of_squares).abs
	end

end

class Squares
	def initialize(upto)
		@upto = upto
	end

	def square_of_sums
		((@upto * (@upto + 1)) / 2) ** 2
	end

	def sum_of_squares
		(@upto*(@upto + 1)*(2 * @upto + 1)) / 6
	end
	
	def difference
		square_of_sums - sum_of_squares
	end
end

class Squares
	def initialize(int)
		@ary = [*1..int]
	end

	def sum_of_squares
		@ary.reduce { |sum, x| sum + x**2 }
	end
	def square_of_sums
		sum_of_ary = @ary.reduce { |sum, x| sum + x } 
		sum_of_ary**2
	end
	def difference
		square_of_sums - sum_of_squares 
	end

end

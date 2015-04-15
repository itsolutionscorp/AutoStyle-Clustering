class Squares
	
	def initialize (numb)
		@numb = numb
	end

	def square_of_sums
		(1..@numb).reduce(:+)**2
	end

	def sum_of_squares
		(1..@numb).inject {|sum, n| sum + n**2}
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

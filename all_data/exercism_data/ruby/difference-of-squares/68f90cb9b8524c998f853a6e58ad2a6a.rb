class Squares

	def initialize(number)
		@number_list = (1..number).to_a
	end

	def square_of_sums
		@number_list.reduce(:+)**2
	end

	def sum_of_squares
		@number_list.reduce {|sum, i| sum + i**2}
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

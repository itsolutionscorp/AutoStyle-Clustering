=begin Pseudocode
how to make this code dry.
information about num is dry
information creates array => this should be in initialize so array can be changed  

=end

class Squares

	def initialize(num)
		@num = num
		@array_nums = [*1..@num]
	end

	def square_of_sums
		return (@array_nums.reduce(:+))**2
	end

	def sum_of_squares
		return @array_nums.reduce{ |sum, n| sum + n**2 }
	end

	def difference
		return square_of_sums - sum_of_squares
	end

end

class Squares

	def initialize(n)
		@n = n
	end

	def sum_of_squares
		(1..@n).inject {|memo,num| memo += num**2}
	end
	
	def square_of_sums
		(1..@n).inject {|memo,num| memo += num}**2
	end

	def difference
		(square_of_sums-sum_of_squares).abs
	end

end

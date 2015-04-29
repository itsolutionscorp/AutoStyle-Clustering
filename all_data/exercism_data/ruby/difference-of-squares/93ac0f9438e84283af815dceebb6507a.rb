class Squares
	def initialize(num)
		@num = num
	end

	def square_of_sums
		(0..@num).inject(:+)**2
	end

	def sum_of_squares
		(0..@num).collect{|n| n**2}.inject(:+)
	end

	def difference
		(sum_of_squares - square_of_sums).abs
	end
end

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
		(self.sum_of_squares - self.square_of_sums).abs
	end

end

print Squares.new(5).sum_of_squares

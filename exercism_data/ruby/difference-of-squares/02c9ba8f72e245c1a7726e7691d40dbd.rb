class Squares

	attr_reader :range

	def initialize(upper_limit)
		@range = (1...upper_limit + 1)
	end

	def square_of_sums()
		range.inject{|sum,x| sum + x } ** 2
	end

	def sum_of_squares()
		range.inject{|sum,x| sum + x ** 2 }
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

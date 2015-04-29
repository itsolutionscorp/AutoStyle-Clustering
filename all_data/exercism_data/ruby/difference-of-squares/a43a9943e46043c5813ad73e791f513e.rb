class Squares

	def initialize value
		@value = value
	end

	def square_of_sums
		@square_of_sums ||= 1.upto(@value).inject( :+ ) ** 2
	end

	def sum_of_squares
		@sum_of_squares ||= 1.upto(@value).inject{ |sum, i| sum + i ** 2 } 
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

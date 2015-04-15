class Squares

	def initialize value
		@value = value
	end

	def square_of_sums
		@square_of_sums ||= begin
			result = 0
			@value.times{ |i| result += ( i + 1 ) }
			result**2
		end
	end

	def sum_of_squares
		@sum_of_squares ||= begin
			result = 0
			@value.times{ |i| result += ( i + 1 ) ** 2 }
			result
		end
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

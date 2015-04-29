class Squares

	def initialize(value)
		@value = value
	end
	
	def square_of_sums
		sum = 0
		range = 1.upto(@value)
		range.each do |x|
			sum = sum + x
		end
		return sum**2
	end


	def sum_of_squares
		sum = 0
		range = 1.upto(@value)
		range.each do |x|
			sum = sum + x**2
		end
		return sum
	end


	def difference
		square_of_sums - sum_of_squares
	end
end

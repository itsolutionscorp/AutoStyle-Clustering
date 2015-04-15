class Squares
	def initialize(n)
		@range = 0..n
	end
	
	def square_of_sums
		result = @range.reduce(:+) ** 2
	end
	
	def sum_of_squares
		result = 0
		@range.each do |x|
			result += (x ** 2)
		end
		result
	end
	
	def difference
		result = square_of_sums() - sum_of_squares()
	end
end

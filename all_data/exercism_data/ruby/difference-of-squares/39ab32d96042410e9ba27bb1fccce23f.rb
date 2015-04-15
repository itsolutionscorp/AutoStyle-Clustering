class Squares

	def initialize(n)
		@n = n
	end

	def square_of_sums
		result = 0
		(1..@n).each do |ith|
			result += ith
		end
		result ** 2
	end

	def sum_of_squares
		result = 0
		(1..@n).each do |ith|
			result += ith ** 2
		end
		result
	end

	def difference
		square_of_sums - sum_of_squares 
	end
end

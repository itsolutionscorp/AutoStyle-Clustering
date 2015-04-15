class Squares

	def initialize(n)
		@n = n
	end
	
	def square_of_sums
		n_sum = 0
		(1..@n).each { |num| n_sum += num }
		return n_sum**2
	end
	
	def sum_of_squares
		n_sum = 0
		(1..@n).each { |num| n_sum += num**2 }
		return n_sum
	end
	
	def difference
		return square_of_sums - sum_of_squares
	end

end

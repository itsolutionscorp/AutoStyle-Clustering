class Squares
	def initialize(num)
		@num = num
	end

	def sum_of_squares
		sum_of_squares = 0
		for i in 1..@num do
			sum_of_squares += i*i
		end 
		sum_of_squares
	end

	def square_of_sums
		sum_of_range = (1..@num).to_a.inject{|sum,x| sum + x}
		square_of_sums = sum_of_range * sum_of_range
		square_of_sums
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

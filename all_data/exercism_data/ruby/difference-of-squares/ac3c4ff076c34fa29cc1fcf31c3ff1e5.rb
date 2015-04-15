class Squares 
	attr_accessor :num

	def initialize num
		@num = num
	end

	def sum_of_squares
		(1..@num).reduce(0){ |sum,n| 
			sum += n**2
		}
	end

	def square_of_sums
		(1..@num).reduce(:+)**2
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

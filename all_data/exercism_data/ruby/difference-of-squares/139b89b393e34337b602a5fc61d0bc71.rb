class Squares
	def initialize(n)
		@number = n
	end
	def square_of_sums
		sum = 0
		(1..@number).each {|i| sum += i}
		sum = sum*sum
	end

	def sum_of_squares		
		sum = 0
		(1..@number).each {|i| sum += i**2 }
		sum 
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

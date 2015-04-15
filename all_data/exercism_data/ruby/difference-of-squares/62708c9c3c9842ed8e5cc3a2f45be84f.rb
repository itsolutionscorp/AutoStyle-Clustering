class Squares
	def initialize(num)
		@num = num
	end

	def square_of_sums
		square = 0
		(1..@num).each { |i| square+=i }
		square = square**2
	end

	def sum_of_squares
		sum = 0
		(1..@num).each { |i| sum+=i**2 }
		sum
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

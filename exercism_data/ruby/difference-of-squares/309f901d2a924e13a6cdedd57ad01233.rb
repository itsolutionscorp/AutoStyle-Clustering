class Squares
	def initialize(n)
		@n = n
	end

	def square_of_sums
		total = (1...@n+1).reduce do |sum, i|
			sum += i 	
		end

		total**2
	end

	def sum_of_squares
		squares = (1...@n+1).map do |i|
			i**2
		end

		squares.reduce do |sum, j|
			sum += j
		end
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

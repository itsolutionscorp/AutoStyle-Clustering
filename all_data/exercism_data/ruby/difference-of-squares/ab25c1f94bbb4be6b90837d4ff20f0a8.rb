class Squares
	def initialize(n)
		@n = n
	end
	def sum_of_squares
		sum = 0
		(1..@n).each do |i|
		  sum = sum + i**2
		end
		return sum
	end
	def square_of_sums
		square = 0
		(1..@n).each do |i|
		  square = square + i
		end
		return square**2
	end
	def difference
		return self.square_of_sums - self.sum_of_squares
	end
end

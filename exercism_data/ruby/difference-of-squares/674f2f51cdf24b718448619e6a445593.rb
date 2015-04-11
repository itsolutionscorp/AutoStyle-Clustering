class Squares
	def initialize(digit)
		@digit = digit
	end
	def self.square_of_sums
		(1..@digit).reduce(:+**2) 
	end
	def self.sum_of_squares
		(1..@digit).reduce(:**2+)
	end
	def difference
		square_of_sums-sum_of_squares
	end
end

class Squares

	def initialize(end_number)
		@end_number = end_number
	end

	def square_of_sums
		sum = 0
		1.step(@end_number,1){ |num| sum += num }
		sum = sum**2
	end
	def sum_of_squares
		sum = 0
		1.step(@end_number,1){ |num| sum += num**2 }
		sum
	end

	def difference
		@difference = self.square_of_sums - self.sum_of_squares
	end

end

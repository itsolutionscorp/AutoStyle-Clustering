class Squares

	def initialize(end_number)
		@end_number = end_number
	end

	def square_of_sums
		result = ( (1..@end_number).inject { |sum, n| sum + n} ** 2)
	end
	def sum_of_squares
		(1..@end_number).inject { |sum, n| sum + n**2 }
	end

	def difference
		@difference ||= self.square_of_sums - self.sum_of_squares
	end

end

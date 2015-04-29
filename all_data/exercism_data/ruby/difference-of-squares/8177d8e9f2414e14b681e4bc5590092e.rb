class Squares
	def initialize(number)
		@number = number.to_i
	end
	def square_of_sums
		1.upto(@number).reduce(:+)**2
	end
	def sum_of_squares
		1.upto(@number).reduce { |sum, n| sum += n**2 }
	end
	def difference
		self.square_of_sums - self.sum_of_squares
	end
end

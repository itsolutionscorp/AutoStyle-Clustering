class Squares

	def initialize(number)
		@number = number
	end
	
	def square_of_sums
		sum = (0..@number).reduce(:+)
		sum ** 2
	end
	
	def sum_of_squares
		(0..@number).inject(0){|memo, element| memo + element**2}
	end
	
	def difference
		self.square_of_sums - self.sum_of_squares
	end

end

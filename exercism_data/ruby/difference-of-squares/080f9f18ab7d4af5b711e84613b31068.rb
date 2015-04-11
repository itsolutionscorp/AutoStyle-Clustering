class Squares

	def initialize(number)
		@number = number
	end

	def square_of_sums
		sum = 0
	  (1..@number).each { |n| sum += n }
	  @square_of_sums = sum ** 2
	end

	def sum_of_squares
		squares = 0
		(1..@number).each { |n| squares += n ** 2 }
		@sum_of_squares = squares
	end

	def difference
		@difference = (self.sum_of_squares - self.square_of_sums).abs
	end
end

class Squares
	def initialize(number)
		@number = number
	end

	def difference
		self.square_of_sums - self.sum_of_squares
	end

	def square_of_sums
		((1..@number).to_a.inject(0){ |sum,num| sum = sum + num }) ** 2
	end

	def sum_of_squares
		(1..@number).to_a.inject(0){ |sum,num| sum = sum + (num * num) }
	end
end

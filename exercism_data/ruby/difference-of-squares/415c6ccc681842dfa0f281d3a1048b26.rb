class Squares
	def initialize(num)
		@num = num
	end

	def square_of_sums
		sum = 0
		(1..@num).each do |x|
			sum += x
		end
		sum**2
	end

	def sum_of_squares
		sum = 0
		(1..@num).each do |x|
			sum += x**2
		end
		sum
	end

	def difference
		self.square_of_sums - self.sum_of_squares
	end
end

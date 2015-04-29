class Squares

	def initialize(val)
		@val = val
	end

	def sum_of_squares
		sum = 0
		(0..@val).each do |n|
			sum += n ** 2
		end
		return sum
	end

	def square_of_sums
		sum = 0
		(0..@val).each do |n|
			sum += n
		end
		return sum ** 2
	end

	def difference
		self.square_of_sums - self.sum_of_squares
	end
end

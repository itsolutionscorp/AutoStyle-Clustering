class Squares
	def initialize(n)
		@n = n
	end

	def difference
		self.square_of_sums - self.sum_of_squares
	end

	def square_of_sums
		i = 1
		sum = 0
		@n.times do
			sum = sum + i
			i = i + 1
		end
		sum ** 2
	end

	def sum_of_squares
		i = 1
		sum = 0
		@n.times do
			sum = sum + (i * i)
			i = i + 1
		end
		sum
	end

end

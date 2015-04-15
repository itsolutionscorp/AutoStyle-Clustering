class Squares
	def initialize n
		@n = n
	end


	# The square of the sum of the first ten natural numbers is,
    # (1 + 2 + ... + 10)**2 = 55**2 = 3025
	def square_of_sums
		(0..@n).reduce(0) { |acc, i| acc + i } ** 2
	end


	# The sum of the squares of the first ten natural numbers is,
    # 1**2 + 2**2 + ... + 10**2 = 385
	def sum_of_squares
		(0..@n).reduce(0) { |acc, i| acc + i ** 2 }
	end


	# Hence the difference between the sum of the squares of the first ten
	# natural numbers and the square of the sum is 3025 - 385 = 2640.
	def difference
		self.square_of_sums - self.sum_of_squares
	end
end

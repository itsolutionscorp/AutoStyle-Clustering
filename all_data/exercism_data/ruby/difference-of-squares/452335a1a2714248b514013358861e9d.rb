class Squares
	def initialize(num)
		@num = num
	end

	def square_of_sums
		sum = 0
		1.upto(@num) { |i| sum += i }
		sum ** 2
	end

	def sum_of_squares
		sum = 0
		1.upto(@num) { |i| sum += (i**2) }
		sum
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

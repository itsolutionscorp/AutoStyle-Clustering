class Squares
	def initialize(num)
		@num = num
	end

	# Returns the sqaure of sums from 0 to num
	def square_of_sums
		count = sum = 0

		@num.times do |i|
			count += 1
			sum += count
		end

		square = sum ** 2
	end

	# Returns the sum of squares from 0 to num
	def sum_of_squares
		count = sum = 0

		@num.times do
			count += 1
			sum += count ** 2
		end

		sum
	end

	# Returns the difference between the two functions square_of_sums to sum_of_squares
	def difference
		diff = square_of_sums - sum_of_squares
	end
end

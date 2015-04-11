class Squares
	def initialize(num)
		@num = num
	end

	def square_of_sums
		count = sum = 0

		@num.times do
			count += 1
			sum += count
		end

		square = sum ** 2
	end

	def sum_of_squares
		count = sum = 0

		@num.times do
			count += 1
			sum += (count ** 2)
		end

		return sum
	end

	def difference
		diff = square_of_sums - sum_of_squares
	end
end

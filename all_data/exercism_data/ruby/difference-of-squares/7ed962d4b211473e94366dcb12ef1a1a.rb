class Squares
	def initialize(value)
		@value = value
	end

	def square_of_sums
		count = @value
		sum = 0
		count.times do
			sum += count
			count -= 1
		end
		total = sum ** 2
		return total
	end

	def sum_of_squares
		count = @value
		total = 0
		count.times do 
			total += count ** 2
			count -= 1
		end
		return total
	end

	def difference
		sum_of_s = sum_of_squares
		squares_of_s = square_of_sums
		return square_of_sums - sum_of_s
	end
end

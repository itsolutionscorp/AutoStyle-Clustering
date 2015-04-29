class Squares
	def initialize(number)
		@number = number
	end
	def sum_of_squares
		sum = 0
		for i in 1..@number do
			sum += i ** 2
			i += 1
		end
		sum
	end

	def square_of_sums
		sum = 0
		for i in 1..@number do
			sum += i
			i += 1
		end
		sum2 = sum ** 2
	end

	def difference
		(sum_of_squares - square_of_sums).abs
	end
end

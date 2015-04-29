class Squares
	def initialize(limit)
		@limit = limit
	end

	def sum_of_squares
		sum = 0
		(1..@limit).each do |num|
			sum += (num ** 2)
		end
		sum
	end

	def square_of_sums
		sum = 0
		(1..@limit).each do |num|
			sum += num
		end
		sum ** 2
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

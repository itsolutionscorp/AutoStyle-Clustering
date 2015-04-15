class Squares

	def initialize(num)
		@num = num
	end
	
	def sum_of_squares
		@sum_of_squares ||= (1..@num).reduce(:+) ** 2
	end
	
	def square_of_sums
		@sum_of_squares ||= (1..@num).map { |n| n ** 2 }.reduce(:+)
	end

	def difference
		@difference ||= square_of_sums - sum_of_squares
	end
end

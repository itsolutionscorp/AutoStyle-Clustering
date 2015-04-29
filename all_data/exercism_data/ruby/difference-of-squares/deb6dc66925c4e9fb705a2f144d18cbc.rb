class Squares
	def initialize(num)
		@one_to_num = 1..num
	end

	def square_of_sums
		@one_to_num.inject(:+) ** 2
	end

	def sum_of_squares
		@one_to_num.map do |n|
			n ** 2
		end.inject(:+)
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

class Squares
	def initialize test_num
		@test_num = test_num
	end

	def square_of_sums
		sum = 0
		1.upto(@test_num) { |a| sum += a }
		sum**2
	end

	def sum_of_squares
		sum = 0
		1.upto(@test_num) { |a| sum += a**2 }
		sum
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

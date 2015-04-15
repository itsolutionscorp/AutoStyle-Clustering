class Squares


	def initialize num
		@num = num
	end

	def square_of_sums
		result = 0
		1.upto(@num) do |i|
			result += i
		end
		result ** 2
	end

	def sum_of_squares
		result = 0
		1.upto(@num) do |j|
			result += j**2
		end
		result
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

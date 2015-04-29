class Squares

	@number
	def initialize(num)
		@number = num
	end

	def square_of_sums
		sums(@number)**2
	end

	def sum_of_squares
		sum = 0
		for i in 1..@number
			sum += i**2
		end
		sum == 0 ? 0 : sum
	end

	def difference
		square_of_sums - sum_of_squares
	end

	private
	def sums (num)
		num == 0 ? 0 : num += sums(num-1)
	end

end

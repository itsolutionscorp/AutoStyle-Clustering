class Squares
	def initialize number
		@number = number
	end
	def square_of_sums
		sum_of_numbers**2
	end
	def sum_of_squares
		sum = 0
		for num in 1..@number do
			sum += num**2
		end
		sum
	end
	def difference
		square_of_sums - sum_of_squares
	end

	private
	def sum_of_numbers
		help_num = @number
		sum = 0
		@number.times do
			sum += help_num
			help_num-=1
		end
		return sum
	end
end

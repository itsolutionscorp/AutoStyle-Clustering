class Squares

	@number
	def initialize(num)
		@number = num
	end

	def square_of_sums
		sums_inject(@number)**2
	end

	def sum_of_squares 
		(1..@number).inject {|sum_squares, element| sum_squares + element**2}
	end

	def difference
		square_of_sums - sum_of_squares
	end

	private
	def sums_recursion (num)
		num == 0 ? 0 : num += sums(num-1)
	end

	def sums_inject (num)
		(1..num).inject {|sum, element| sum + element}
	end

	def sum_of_squares_loop
		sum = 0
		for i in 1..@number
			sum += i**2
		end
		sum == 0 ? 0 : sum
	end

	

end

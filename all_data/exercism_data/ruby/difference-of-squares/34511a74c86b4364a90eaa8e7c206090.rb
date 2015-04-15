class Squares

	def initialize(number)
		@number = number
	end


	def sum_of_squares
		sum = 0
		(1..@number).each{|x| sum += x*x }
		sum
	end

	def square_of_sums
		sum = 0
		(1..@number).each{|x| sum += x}
		sum * sum
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

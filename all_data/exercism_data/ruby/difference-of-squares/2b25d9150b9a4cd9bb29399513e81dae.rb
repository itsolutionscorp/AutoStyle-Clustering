class Squares
	attr_accessor :num

	def initialize(number)
		@num = number		
	end

	def square_of_sums
		sum = (1..num).inject(0,:+) { n }
		sum * sum
	end

	def sum_of_squares
		(1..num).each_with_object([]) { |n,sq| sq << n*n }.inject(0,:+) { n }
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

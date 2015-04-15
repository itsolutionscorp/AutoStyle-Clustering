class Squares

	def initialize(num)
		@number = 0.upto(num)
	end

	def square_of_sums
		sum = 0
		@number.each{ |num| sum += num }
		@sq_of_sums ||= sum**2
	end

	def sum_of_squares
		sum = 0
		@number.each{ |num| sum += num**2 }
		@sum_of_sqs ||= sum
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

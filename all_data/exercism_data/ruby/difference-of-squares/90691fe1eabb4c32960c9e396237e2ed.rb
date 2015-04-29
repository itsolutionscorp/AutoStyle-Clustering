class Squares
	def initialize(number)
		@number = number
		@sqs = 0
		@suq = 0
	end
	def square_of_sums
		(1..@number).each do |n|
			@sqs += n
		end
		@sqs**2
	end
	def sum_of_squares
		(1..@number).each do |n|
			@suq += ((n)**2)
		end
		@suq
	end
	def difference
		square_of_sums - sum_of_squares
	end
end

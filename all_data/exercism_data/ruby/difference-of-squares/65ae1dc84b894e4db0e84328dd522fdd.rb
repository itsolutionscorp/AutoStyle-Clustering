class Squares
	@n
	def initialize(n)
		@n = n
	end

	def square_of_sums
		num = 0
		@n.times do |i|
			num += i+1
		end
		num**2
	end

	def sum_of_squares
		sum = 0
		@n.times do |i|
			sum += (i+1)**2
		end
		sum
	end

	def difference
		square_of_sums - sum_of_squares
	end

end

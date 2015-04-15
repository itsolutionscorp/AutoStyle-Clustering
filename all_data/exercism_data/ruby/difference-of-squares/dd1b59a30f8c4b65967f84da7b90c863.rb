class Squares
	class << self

	end

	def initialize(number)
		@number = number
	end

	def square_of_sums
		sum = 0
		(1..@number).each do |number|
			sum += number
		end
		sum**2
	end

	def sum_of_squares
		sum = 0
		(1..@number).each do |number|
			sum += number**2
		end
		sum
	end

	def difference
		(sum_of_squares - square_of_sums).abs
	end
	
end

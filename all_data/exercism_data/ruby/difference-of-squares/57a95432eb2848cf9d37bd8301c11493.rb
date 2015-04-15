class Squares

	attr_reader :num
	
	def initialize(num)
		@num = num
	end
	
	
	def square_of_sums
		individual_sum = 0
		(0..num).each do |i|
		 individual_sum += i
		end
		
		square_sum = individual_sum**2
		square_sum
	end
	
	def sum_of_squares
		 sum = 0
		 (0..num).each do |i|
		   sum += i**2
		 end
		 sum
	end
	
	def difference
		 (square_of_sums)-(sum_of_squares)
	end
	
end


p Squares.new(10).difference

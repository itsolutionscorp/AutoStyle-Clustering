class Squares
	attr_reader :range
	
	def initialize (new_value)
		@range = 1.upto(new_value)
	end

	def square_of_sums
		range.reduce(:+)**2
	end

	def sum_of_squares
		range.map{|i| i**2}.reduce(:+)
	end

	def difference 
		square_of_sums - sum_of_squares
	end
end

class Squares
	def initialize(argument)
		@natural_number = argument
	end
	
	def square_of_sums
		sums = 0
		1.upto(@natural_number) do |x| sums += x  end
		return sums**2
	end

	def sum_of_squares
		sum_of_squares = 0
		1.upto(@natural_number) do |x| sum_of_squares += (x**2)  end 
		return sum_of_squares
	end

	def difference
		difference = square_of_sums - sum_of_squares
		return difference
	end
end



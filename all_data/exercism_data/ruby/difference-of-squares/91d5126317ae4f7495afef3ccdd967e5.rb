class Squares

		def initialize(val)
			@sum_of_squares = 0
			@sum = 0
			@square_of_sums = 0
	
			1.upto(val) do |i|
				@sum_of_squares += (i**2)
				@sum += i
			end
		end
			
		def sum_of_squares
			@sum_of_squares
		end
		
		def square_of_sums
			@sum**2 
		end
		
		def difference
			@sum**2 - @sum_of_squares
		end

end

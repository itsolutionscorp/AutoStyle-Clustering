class Squares
		
		attr_accessor :no
		
		def initialize no
			@no=no
		end
	
		def sum_of_squares
			(1..self.no).inject { |accumul, n| accumul + (n**2) }
		end
		
		
		def difference
			( (1..self.no).reduce(:+) **2 ) - sum_of_squares
		end
		
		def square_of_sums
			((1..self.no).reduce(:+) **2)
		end

end

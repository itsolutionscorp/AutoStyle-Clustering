class Squares	
	def initialize num
		@num = num
	end
	def square_of_sums
		return (@num*(@num+1)/2)**2
	end
	def sum_of_squares
		return (@num*(@num+1)*(2*@num+1)/6)
	end
	def difference
		return Squares.new(@num).square_of_sums - Squares.new(@num).sum_of_squares 
	end
end

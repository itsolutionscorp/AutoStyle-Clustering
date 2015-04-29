class Squares
	def initialize(num)
		@num=num
	end
	def sum_of_squares
		i=0
		sum=0
		while i<=@num
			sum=sum+i**2
			i=i+1
		end
		return sum
	end
	
	def square_of_sums
		i=0
		sum=0
		while i<=@num
			sum=sum+i
			i=i+1
		end
		return sum**2
	end
	
	def difference
		square_of_sums-sum_of_squares
	end
	
	
end

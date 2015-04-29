class Squares

	def initialize(numbers)
     @numbers = numbers
  	end

  	def square_of_sums
  		((1..@numbers).to_a.inject(:+)) **2
  	end

  	def sum_of_squares
  		(1..@numbers).inject {|result, num| result += num * num  }
  	end

  	def difference
  		square_of_sums - sum_of_squares 
  	end

end

class Squares

	def initialize(numbers)
     @numbers = numbers
  	end

  	def square_of_sums
  		val = (1..@numbers).to_a.inject(:+)
  		val * val
  	end

  	def sum_of_squares
  		result = 0
  		(1..@numbers).each {|num| result += num * num  }
  		result
  	end

  	def difference
  		square_of_sums - sum_of_squares 
  	end

end

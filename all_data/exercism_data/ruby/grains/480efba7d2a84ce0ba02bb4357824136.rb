#!/usr/bin/env ruby

class Grains
	#takes the square number as input, returns the number of grains on that square
	def square(squarenum)		
		return 2**(squarenum - 1)
	end

	#returns the total number of grains on the entire board
	def total
		runningtotal = 0
		(1..64).each do |x|
			runningtotal += square(x)
		end
		
		return runningtotal
	end
		
end

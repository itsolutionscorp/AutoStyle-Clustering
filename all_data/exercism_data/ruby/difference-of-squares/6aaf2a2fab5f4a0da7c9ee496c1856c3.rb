#!/usr/bin/env ruby

class Squares
	def initialize(intinput)
		@intinput = intinput
	end

	def square_of_sums
		sum = 0
		#puts @intinput
		(1..@intinput).each do |i|
			sum += i
		end
		
		return sum**2
		#return 225
	end

	def sum_of_squares
		sumsqr = 0
		(1..@intinput).each do |j|
			sumsqr += j**2
		end

		return sumsqr
	end

	def difference
		return square_of_sums - sum_of_squares
	end
end

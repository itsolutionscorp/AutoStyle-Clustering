=begin
  File: squares.rb
  Author: sherinom
=end

class Squares

	def initialize(n)
		@limit = n
	end

	def square_of_sums
		total = 0
		(1..@limit).each do |n|
			total += n
		end
		return total**2
	end	
	
	def sum_of_squares
		total = 0
		(1..@limit).each do |n|
			total += n**2
		end
		return total
	end

	def difference
		square_sums = square_of_sums
		sum_squares = sum_of_squares
		return [square_sums, sum_squares].max - [square_sums, sum_squares].min
	end
	
end

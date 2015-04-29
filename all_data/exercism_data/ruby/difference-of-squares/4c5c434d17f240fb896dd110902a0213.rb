class Squares

	def initialize(n)
		@n = n
	end

	def square_of_sums
		(1..@n).reduce(:+)**2	
	end

	def sum_of_squares
		(1..@n).map { |n| n**2 }.reduce(:+)						
	end

	def difference
		square_of_sums - sum_of_squares 
	end
end

t = 10

puts Squares.new(t).square_of_sums
puts Squares.new(t).sum_of_squares

puts Squares.new(t).difference

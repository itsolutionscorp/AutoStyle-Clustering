class Squares
	def initialize(number)
		@range = (1..number).to_a
	end

	def square_of_sums
		@range.reduce(:+)**2
	end

	def sum_of_squares
		@range.map { |i| i**2 }.inject { |sum, n| sum + n }
	end

	def difference
		self.square_of_sums - self.sum_of_squares
	end
end

puts Squares.new(10).difference

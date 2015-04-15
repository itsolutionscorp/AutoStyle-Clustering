class Squares
	def initialize(x) @x = x end
	def square_of_sums() sum = (1..@x).to_a.inject(:+) ** 2 end #sum up numbers then square
	def sum_of_squares() sum = (1..@x).to_a.inject(0) { |sum, i| sum + i**2 } end #sum up squares
	def difference() square_of_sums - sum_of_squares end
end

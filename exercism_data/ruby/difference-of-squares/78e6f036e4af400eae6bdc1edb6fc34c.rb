class Squares
	def initialize(num)
		self.num = num
	end

	def square_of_sums
		(1..num).inject(:+)**2
	end

	def sum_of_squares
		(1..num).collect { |n| n**2 }.inject(:+)
	end

	def difference
		sum_of_squares > square_of_sums ? sum_of_squares - square_of_sums : square_of_sums - sum_of_squares
	end

	private
	attr_accessor :num
end

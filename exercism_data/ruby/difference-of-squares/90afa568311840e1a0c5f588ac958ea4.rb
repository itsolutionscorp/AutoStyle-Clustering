class Squares
	attr_accessor :num
	def initialize(num)
		@num = num
	end

	def square_of_sums
		(0..num).inject(&:+)**2
	end

	def sum_of_squares
		(0..num).map {|n| n**2}.inject(&:+)
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

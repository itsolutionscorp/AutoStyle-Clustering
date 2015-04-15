class Squares
	attr_accessor :num
	
	def initialize(num)
		@num = num
	end
	
	def square_of_sums
		sq = (1..@num).inject(:+)
		sq * sq
	end
	
	def sum_of_squares
		(1..@num).inject(0) { |memo, n| memo += n * n }
	end
	
	def difference
		self.square_of_sums - self.sum_of_squares
	end
end

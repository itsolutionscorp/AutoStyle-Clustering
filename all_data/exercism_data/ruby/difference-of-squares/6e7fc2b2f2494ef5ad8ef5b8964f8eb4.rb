class Squares
  def initialize digit
		@digit = digit
  end
	
	def square_of_sums
		@sqr_sums = 0
		1.upto(@digit) {|d| @sqr_sums += d}
		@sqr_sums **= 2
	end

	def sum_of_squares
		@sum_sqrs = 0
		1.upto(@digit) {|d| @sum_sqrs += d**2 }
		@sum_sqrs
	end

	def difference
		square_of_sums - sum_of_squares	
	end
end

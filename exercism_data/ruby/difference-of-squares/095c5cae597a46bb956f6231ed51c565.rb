class Squares

	def initialize(cycles)
		@cycles = cycles
	end

	def sum_of_squares
		 bin = []
		 totalled_squares = 0
		 bin = (1..@cycles).map { |x| x ** 2}
		 totalled_squares = bin.inject(0) {|sum, i| sum + i}
  end

	def square_of_sums
		 bin = []
		 subtotal = 0
		 squared_sum = 0
		 bin = (1..@cycles).map { |x| x }
		 subtotal = bin.inject(0) {|sum, i| sum + i}
		 squared_sum = subtotal ** 2
	end

	def difference
		 bin = []
		 totalled_squares = 0
		 bin = (1..@cycles).map { |x| x ** 2}
		 totalled_squares = bin.inject(0) {|sum, i| sum + i}

		 bin = []
		 subtotal = 0
		 squared_sum = 0
		 bin = (1..@cycles).map { |x| x }
		 subtotal = bin.inject(0) {|sum, i| sum + i}
		 squared_sum = subtotal ** 2

		difference = squared_sum - totalled_squares
		return difference
	end

end

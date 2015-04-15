class Squares

	def initialize(nb)
		@nb = nb.to_i
	end

	def sum_of_squares
		i = 0
		sum = 0
		while i <= @nb
			sum += i**2
			i += 1
		end
		return sum
	end

	def square_of_sums
		i = 0
		sum = 0
		while i <= @nb
			sum += i
			i += 1
		end
		return sum**2
	end

	def difference
		Squares.new(@nb).square_of_sums - Squares.new(@nb).sum_of_squares
	end
end

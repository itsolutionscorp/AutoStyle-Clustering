class Squares
	def initialize(nb)
		@nbr = nb.to_i
	end

	def sum_of_squares
		i = 1
		sum = 0
		while i <= @nbr
			sum = sum + i**2
			i += 1
		end
		return sum
	end

	def square_of_sums
			i = 1
		sum = 0
		while i <= @nbr
			sum = sum + i
			i += 1
		end
		return sum**2
	end

	def difference
		return (sum_of_squares - square_of_sums).abs
	end
end

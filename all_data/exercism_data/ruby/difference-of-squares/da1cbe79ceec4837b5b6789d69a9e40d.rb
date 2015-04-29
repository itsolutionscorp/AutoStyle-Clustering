class Squares 

	def initialize(number)
		@range = 1..number
	end

	def add(range)
		range.reduce(:+)
	end

	def find_square(number)
		number**2
	end

	def sum_of_squares
		squares_array = @range.map { |number| find_square(number)}
		add(squares_array)
	end

	def square_of_sums
		number = add(@range)
		find_square(number)
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

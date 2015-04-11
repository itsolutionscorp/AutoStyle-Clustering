class Squares
	attr_reader :number 

	def initialize(number)
		@number = number 
	end

	def sum_of_squares
		s = (1..number).to_a
		s.map { |s| s ** 2  }.inject(:+)
	end

	def square_of_sums
		s = (1..number).to_a.inject(:+)
		s**2
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

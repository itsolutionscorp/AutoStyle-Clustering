class Squares


	attr_accessor :array

	def initialize(number)
		@array = []
		(1..number).each do |x|
			@array.push(x)
		end
	end

	def sum_of_squares
		@array.collect {|x| x*x }.inject(:+)	
	end

	def square_of_sums
		@array.inject(:+) * @array.inject(:+)
	end

	def difference
		square_of_sums - sum_of_squares
	end
end

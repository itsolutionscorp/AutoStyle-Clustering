class Squares
	attr_accessor :value

	def initialize(num) 
		@value = num
	end

	def square_of_sums
		return 1.upto(@value).reduce(:+)**2
	end

	def sum_of_squares
		return 1.upto(@value).inject { |sum, elt| sum += elt**2 }
	end

	def difference
		return self.square_of_sums - self.sum_of_squares
	end

end

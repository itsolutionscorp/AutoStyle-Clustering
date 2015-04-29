class Squares

	# Public: initialies Squares object
	#
	# count: Integer, the number of natural numbers to work with
	def initialize(count)
		@range = (1..count)
	end

	# Public: Sums the natural numbers for the count of the Object and 
	#          returns the square of that sum
	#
	# Returns Integer 
	def square_of_sums
		sq_it(@range.inject(:+))
	end

	# Public: Squares all numbers between 1 && count of Object, summing them 
	#         together.
	#
	# Returns Integer
	def sum_of_squares
		@range.inject { |sum,n| sum + sq_it(n) }
	end

	# Public: Returns the difference between between the 
	#          square_of_sums and the sum_of_squares
	# 
	# Returns Integer
	def difference
		square_of_sums - sum_of_squares
	end

	private
		# Internal: a simple class to return the Square of a number
		#
		# number: integer
		#
		# Returns integer
		def sq_it(num)
			num **2
		end

end

class Squares

	# Create an instance of Squares
	# ============================================
	# parameters: 
	# n_numbers - the maximum natural
	#             number you want to calculate the 
	#             sqare of sums, sum of squares, 
	# 			  and difference with.
	def initialize(n_numbers)
		@n_numbers = n_numbers
	end

	# Calculates and returns the square of sums
	# ============================================
	# (1 + 2 + ... + n_numbers)**2
	def square_of_sums()
		sum = 0
		for n in 1..@n_numbers
			sum += n
		end
		sum **= 2
		return sum
	end

	# Calculates and returns the sum of squares
	# =============================================
	# 1**2 + 2**2 + ... + n_numbers**2
	def sum_of_squares()
		sum = 0
		for n in 1..@n_numbers
			sum += n**2
		end
		return sum
	end

	# Calculates and returns the difference between
	# the square of sums and the sum of squares
	# =============================================
	# square of sums - sum of squares
	def difference()
		return square_of_sums - sum_of_squares
	end
end

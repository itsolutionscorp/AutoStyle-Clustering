def square_of_sum(upperNum):
	"""Square the sum of natural numbers up to the given number."""
	total = 0
	for x in range(1, upperNum + 1):
		total += x
	return total ** 2


def sum_of_squares(upperNum):
	"""Sum the squares of natural numbers up to the given number."""
	total = 0
	for x in range(1, upperNum + 1):
		total += (x ** 2)
	return total


def difference(upperNum):
	"""Find the difference between the sum of the squares and the square of the sum."""
	return abs(sum_of_squares(upperNum) - square_of_sum(upperNum))


# Author's Notes on Coding Conventions:

	# I've coded to pep8 conventions with a few exceptions that
	# work nicely with my setup (I'm only coding for myself):
		# ignore = W191,E261,W293,E701
		# max-line-length = 120


# # Difference Of Squares

# Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

# The sum of the squares of the first ten natural numbers is,

#     1**2 + 2**2 + ... + 10**2 = 385

# The square of the sum of the first ten natural numbers is,

#     (1 + 2 + ... + 10)**2 = 55**2 = 3025

# Hence the difference between the sum of the squares of the first ten
# natural numbers and the square of the sum is 3025 - 385 = 2640.

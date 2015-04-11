def difference(a):
 	print 'final' , square_of_sum(a) - sum_of_squares(a)

def square_of_sum(a):
	return sum(range(1, a+1)) ** 2 


def sum_of_squares(a):
	return sum([i**2 for i in range(1, a+1)])


# square_of_sum(10)

# sum_of_squares(10)

# difference(10)

#  Find the difference between the sum of the squares and the square of the sums of the first N natural numbers.

# The sum of the squares of the first ten natural numbers is,

#     1**2 + 2**2 + ... + 10**2 = 385

# The square of the sum of the first ten natural numbers is,

#     (1 + 2 + ... + 10)**2 = 55**2 = 3025

# Hence the difference between the sum of the squares of the first ten
# natural numbers and the square of the sum is 3025 - 385 = 2640.

'''
difference_of_squares.py

Find the difference between the sum of the squares and the square of the sums of the first N natural numbers.

The sum of the squares of the first ten natural numbers is,

    1**2 + 2**2 + ... + 10**2 = 385

The square of the sum of the first ten natural numbers is,

    (1 + 2 + ... + 10)**2 = 55**2 = 3025

Hence the difference between the sum of the squares of the first ten
natural numbers and the square of the sum is 3025 - 385 = 2640.
'''

def square_of_sum(value):
    return sum(range(1, value + 1))**2

def sum_of_squares(value):
    return sum([v**2 for v in range(1, value + 1)])

def difference(value):
    return square_of_sum(value) - sum_of_squares(value)

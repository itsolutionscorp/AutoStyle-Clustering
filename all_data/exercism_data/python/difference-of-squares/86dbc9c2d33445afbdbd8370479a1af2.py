from functools import reduce

def difference(number):
    return square_of_sum(number) - sum_of_squares(number)

def sum_of_squares(number):
    return sum(map(lambda x: pow(x, 2), xrange(1, number + 1)))

def square_of_sum(number):
    return pow(sum(xrange(1, number + 1)), 2)

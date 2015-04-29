def square_of_sum(number):
    return sum(xrange(1, number+1)) ** 2

def sum_of_squares(number):
    return sum(n ** 2 for n in xrange(1, number+1))

def difference(number):
    return square_of_sum(number) - sum_of_squares(number)

def difference(number):
    return square_of_sum(number)-sum_of_squares(number)


def square_of_sum(number):
    return sum([i+1 for i in xrange(number)])**2


def sum_of_squares(number):
    return sum([(i+1)**2 for i in xrange(number)])

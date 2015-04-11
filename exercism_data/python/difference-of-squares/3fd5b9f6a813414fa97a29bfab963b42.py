def sum_of_squares(number):
    return sum([int(x**2) for x in xrange(number+1)])


def square_of_sum(number):
    return sum([int(x) for x in xrange(number+1)])**2


def difference(number):
    return square_of_sum(number) - sum_of_squares(number)

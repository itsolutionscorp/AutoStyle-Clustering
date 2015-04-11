def square_of_sum(num):
    return sum(xrange(num+1)) ** 2

def sum_of_squares(num):
    return sum(x**2 for x in xrange(1, num+1))


def difference(num):
    return abs(sum_of_squares(num)-square_of_sum(num))

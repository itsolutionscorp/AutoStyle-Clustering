# Are tupples faster? Apparently not in this use.


def square_of_sum(num):
    sums = sum([x for x in xrange(0, num + 1)])
    return sums**2


def sum_of_squares(num):
    sums = sum([x**2 for x in xrange(0, num + 1)])
    return sums


def difference(num):
    diff = sum_of_squares(num) - square_of_sum(num)
    return abs(diff)

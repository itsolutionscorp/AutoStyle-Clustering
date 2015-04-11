def square_of_sum(num):
    return reduce(lambda x, y: x + y, xrange(1, num + 1)) ** 2

def sum_of_squares(num):
    return reduce(lambda x, y: x + y ** 2, xrange(1, num + 1))

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)

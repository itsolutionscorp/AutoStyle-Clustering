__author__ = 'tracyrohlin'

def square_of_sum(n):
    sq_sum = 0
    for x in range(n+1):
        sq_sum += x
    sq_sum = sq_sum ** 2

    return sq_sum


def sum_of_squares(n):
    sum_num = 0
    for x in xrange(n+1):
        x = x**2
        sum_num += x

    return sum_num

def difference(n):
    total = square_of_sum(n) - sum_of_squares(n)
    return total

print difference(5)

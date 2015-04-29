def square_of_sum(number):

    return sum(xrange(number + 1))**2

def sum_of_squares(number):

    return sum(map(lambda x : x**2, xrange(number + 1)))

def difference(number):

    return square_of_sum(number) - sum_of_squares(number)

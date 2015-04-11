"""from difference_of_squares import difference, square_of_sum, sum_of_squares

"""


def difference(i):
    """ calculate the difference """
    return square_of_sum(i) - sum_of_squares(i)


def sum_of_squares(i):
    """ sum of squares """
    return sum([j**2 for j in range(1, i+1)])


def square_of_sum(i):
    """ square the sum """
    return sum([j for j in range(1, i+1)])**2

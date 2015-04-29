"""
difference_of_squares

"""


def difference(n):
    """
    Get difference of square_of_sum and sum_of_squares

    :param n:
    :return:
    """
    return square_of_sum(n) - sum_of_squares(n)


def square_of_sum(n):
    """
    Get (1 + 2 + ... + n)**2

    :param n:
    :return:
    """
    return sum(x for x in range(n+1))**2


def sum_of_squares(n):
    """
    Get (1**2 + 2**2 + ... + n**2)

    :param n:
    :return:
    """
    return sum(x**2 for x in range(n+1))

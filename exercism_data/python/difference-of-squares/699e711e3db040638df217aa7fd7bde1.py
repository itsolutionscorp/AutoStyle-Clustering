def difference(num):
    x = square_of_sum(num)
    y = sum_of_squares(num)
    return abs(x-y)


def square_of_sum(num):
    """
    The square of the sum of the first ten natural numbers is,
    (1 + 2 + ... + 10)**2 = 55**2 = 3025
    """
    counter = 0
    for i in range(1, num+1):
        counter += i

    return counter**2


def sum_of_squares(num):
    """The sum of the squares of the first ten natural numbers is,
    1**2 + 2**2 + ... + 10**2 = 385
    """

    counter = 0
    for i in range(1, num+1):
        counter += i**2

    return counter

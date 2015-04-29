def sum_of_squares(n):
    """ http://en.wikipedia.org/wiki/Square_pyramidal_number """
    return (2 * n**3 + 3 * n**2 + n) / 6

def square_of_sum(n):
    """ http://en.wikipedia.org/wiki/Triangular_number """
    return (n * (n + 1) / 2) ** 2

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

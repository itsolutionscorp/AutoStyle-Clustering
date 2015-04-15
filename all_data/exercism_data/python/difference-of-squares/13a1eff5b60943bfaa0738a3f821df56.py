def square_of_sum(n):
    """
    Square of sum 1..n

    Using the triangle number formula
    """
    return ((n * (n+1)) / 2)**2


def sum_of_squares(n):
    """
    Sum of all squares 1..n**2

    Using the sum of squares formula
    """
    return (n * (n+1) * (2*n + 1)) / 6


def difference(n):
    """
    Difference between the sum of squares 1..n and square of sums 1..n
    """
    return abs(square_of_sum(n) - sum_of_squares(n))

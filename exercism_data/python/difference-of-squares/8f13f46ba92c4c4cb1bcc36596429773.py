def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    sum_to_square = sum(xrange(1, n+1))
    return sum_to_square ** 2

def sum_of_squares(n):
    squares = map(lambda x: x**2, xrange(1, n+1))
    return sum(squares)

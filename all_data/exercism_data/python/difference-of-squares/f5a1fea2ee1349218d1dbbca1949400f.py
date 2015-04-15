def _square(n):
    return n*n

def square_of_sum(n):
    return _square((n * (n + 1))/2)

def sum_of_squares(n):
    return sum(map(_square, range(1, n+1)))

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

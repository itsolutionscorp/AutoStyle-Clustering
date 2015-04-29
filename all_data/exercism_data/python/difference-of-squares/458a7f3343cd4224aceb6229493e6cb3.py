def difference(n):
    return abs(square_of_sum(n) - sum_of_squares(n))

def sum_of_squares(n):
    return reduce(lambda m, x: x**2 + m, xrange(n+1), 0)

def square_of_sum(n):
    return sum(range(n+1))**2

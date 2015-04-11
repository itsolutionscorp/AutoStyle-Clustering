def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    n_sum = sum([(x+1) for x in range(n)])
    return n_sum**2

def sum_of_squares(n):
    n_squares = [(x+1)**2 for x in range(n)]
    return sum(n_squares)

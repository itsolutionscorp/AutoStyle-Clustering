def seq(n):
    return range(1, n+1)

def square_of_sum(n):
    return sum(seq(n))**2

def sum_of_squares(n):
    return sum([i**2 for i in seq(n)])

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    r = sum(range(1, n+1)) ** 2
    return r

def sum_of_squares(n):
    r = sum(i**2 for i in range(1, n+1))
    return r

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def difference(n):
    return abs(sum_of_squares(n) - square_of_sum(n))

def square_of_sum(n):
    return sum(i for i in range(1, n+1))**2

def sum_of_squares(n):
    return sum(i**2 for i in range(1, n+1))

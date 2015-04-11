def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    return reduce(lambda a, e: a + e, range(1, n+1))**2
    
def sum_of_squares(n):
    return reduce(lambda a, e: a + e**2, range(1, n+1))

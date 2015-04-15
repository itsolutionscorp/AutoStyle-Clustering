def sum_of_squares(n):
    return sum([x**2 for x in range(1, n+1)])
    
def square_of_sum(n):
    sum = (1 + n) * (n / 2.0)
    return sum**2

def difference(n):
    return abs(sum_of_squares(n) - square_of_sum(n))
    
    

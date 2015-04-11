# Difference of Squares Python exercism, 1st iteration

def sum_of_first(n):
    return (n * (n+1))/2
    
def square_of_sum(n):
    return sum_of_first(n) ** 2
    
def sum_of_squares(n):
    return (n * (n+1) * (2 * n + 1))/6
    
def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

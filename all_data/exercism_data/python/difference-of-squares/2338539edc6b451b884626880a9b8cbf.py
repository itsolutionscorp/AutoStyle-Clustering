def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    m = sum(range(1,n+1))
    return m*m
    
def sum_of_squares(n):
    return sum([i*i for i in range(1,n+1)])

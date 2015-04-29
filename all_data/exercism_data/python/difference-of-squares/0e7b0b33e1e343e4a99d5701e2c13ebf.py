def square_of_sum(n):
    return ((n+1)*n/2)**2

def sum_of_squares(n):
    total =0
    for p in range(1,n+1):
        total+=p**2
    return total

def difference(n):
    return abs(square_of_sum(n) - sum_of_squares(n))
    

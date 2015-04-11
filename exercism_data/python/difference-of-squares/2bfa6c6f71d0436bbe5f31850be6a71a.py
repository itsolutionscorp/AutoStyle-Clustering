def sum_of_squares(n):
    if n == 1:
        return 1
    else:
        return n**2 + sum_of_squares(n-1)

def square_of_sums(n):
    sum = ((n+1)*n)/2
    squared = sum**2
    return squared
    
def difference(n):
    return square_of_sums(n) - sum_of_squares(n)

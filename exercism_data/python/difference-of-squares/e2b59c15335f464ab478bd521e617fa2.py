def square_of_sum(n):
    n_sum =  (n**2 + n) / 2
    return n_sum**2

def sum_of_squares(n):
    return (2*n**3 + 3*n**2 + n) / 6

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

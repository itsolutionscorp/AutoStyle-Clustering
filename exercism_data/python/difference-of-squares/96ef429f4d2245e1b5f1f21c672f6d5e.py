#Rewrite using sum function
def sum_of_squares(n):
    return sum(i**2 for i in xrange(n+1))
    
def square_of_sum(n):
    return sum(i for i in xrange(n+1))**2
    
def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

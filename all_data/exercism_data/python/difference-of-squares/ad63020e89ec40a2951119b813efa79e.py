import numpy as np

def difference(n):
    
    return square_of_sum(n) -sum_of_squares(n) 

def square_of_sum(n):
    
    return np.sum(np.arange(1, n+1))**2

def sum_of_squares(n):
    
    return np.sum(np.arange(1, n+1)**2)

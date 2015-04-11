# -*- coding: utf-8 -*-
#
#
#from difference_of_squares import difference, square_of_sum, sum_of_squares
#
#
def sum_of_squares(N):
    
    sums = 0
    
    y = [(x + 1)**2 for x in range(N)]

    for x in y:
        sums += x
        
    return sums 
    
def square_of_sum(N):
    
    square = 0
    
    for x in range(N):
        square += x + 1
    
    return square**2

def difference(N):
    
    return square_of_sum(N) - sum_of_squares(N)

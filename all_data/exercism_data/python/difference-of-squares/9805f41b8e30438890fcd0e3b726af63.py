'''
Created on Oct 22, 2014

@author: jbarni00
'''

def difference(n):
    return  square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    return sum(range(1,n +1)) ** 2

def sum_of_squares(n):
    return sum ([r**2 for r in range(1,n+1)])
    

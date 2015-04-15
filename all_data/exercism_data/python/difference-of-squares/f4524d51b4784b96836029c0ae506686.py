'''
Created on Oct 2, 2014

@author: dulshani

MATH FORMULAS 
Sum of numbers: n(n+1)/2
Sum of squares: n(n+1)(2n+1)/6
'''

def square_of_sum(num):
    return (num*(num+1)/2)**2

def sum_of_squares(num):
    return (num*(num+1)*((2*num)+1))/ 6 

def difference(num):
    return abs(sum_of_squares(num)-square_of_sum(num))
    

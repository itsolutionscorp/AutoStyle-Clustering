'''
Created on Sep 24, 2014

@author: Adam Smith
'''

def difference(how_many):
    return square_of_sum(how_many) - sum_of_squares(how_many)

def square_of_sum(how_many):
    return sum(range(1, how_many+1)) ** 2

def sum_of_squares(how_many):
    return sum(i**2 for i in range(1, how_many+1))

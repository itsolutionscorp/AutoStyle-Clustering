def square_of_sum(num):   
    return sum_squares_of_natural_number(num) + square_of_sum(num)

def difference(num):    
    return square_of_sum(num) - sum_squares_of_natural_number(num) 

def sum_squares_of_natural_number(num):
    sum_squares_of_natural_number = 0
    for i in range(1, num):
        sum_squares_of_natural_number += num**2
    return sum_squares_of_natural_number

def square_of_sum(num):
    sum = 0
    for i in range(1, num):
        sum += num
    return sum**2

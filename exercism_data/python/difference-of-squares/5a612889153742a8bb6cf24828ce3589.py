#!/usr/bin/python
def difference(input_value):
    return square_of_sum(input_value) - sum_of_squares(input_value)

def square_of_sum(input_value):
    return sum(range(1, input_value+1))**2

def sum_of_squares(input_value):
    sum = 0
    for i in range(1,input_value + 1):
        sum += i**2
    return sum

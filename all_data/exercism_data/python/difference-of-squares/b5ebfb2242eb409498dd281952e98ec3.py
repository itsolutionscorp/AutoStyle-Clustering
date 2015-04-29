__author__ = 'Hinek'

def sum_of_squares(num):
    result = 0
    for n in range(num+1):
        result += n**2
    return result

def square_of_sum(num):
    result = 0
    for n in range(num+1):
        result += n
    return result**2

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)

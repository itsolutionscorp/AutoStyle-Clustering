__author__ = 'flux'

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)

def square_of_sum(num):
    sum = 0
    for x in range (1, num+1):
        sum += x
    sumsq = sum**2
    return sumsq
def sum_of_squares(num):
    sum = 0
    for x in range (1, num+1):
        sum += x**2
    return sum

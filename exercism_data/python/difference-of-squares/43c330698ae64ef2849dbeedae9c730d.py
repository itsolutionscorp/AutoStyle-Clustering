__author__ = 'banarasitippa'

def difference(last_num):
    '''
    difference between the sum of the squares of the all natural numbers until last_num+1
    :param last_num:
    :return:
    '''
    return  square_of_sum(last_num) - sum_of_squares(last_num)

def square_of_sum(last_num):
    '''
    square of the sum of the first ten natural numbers until last_num+1
    :param last_num: int
    :return: int
    '''
    return (sum(range(last_num+1))**2)

def sum_of_squares(last_num):
    '''
    sum of the squares of the first ten natural numbers until last_num+1
    :param last_num: int
    :return: int
    '''
    return sum([x**2 for x in range(last_num+1)])

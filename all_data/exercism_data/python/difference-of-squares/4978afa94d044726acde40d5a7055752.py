""" Exercism - Difference of Squares """

def square_of_sum(num):
    """ Return the (sum of the numbers)^2 """
    return sum(range(num + 1)) ** 2

def sum_of_squares(num):
    """ Return the sum(numbers^2) """
    return sum(x*x for x in range(num + 1))

def difference(num):
    """ Find the difference between the two """
    return square_of_sum(num) - sum_of_squares(num)

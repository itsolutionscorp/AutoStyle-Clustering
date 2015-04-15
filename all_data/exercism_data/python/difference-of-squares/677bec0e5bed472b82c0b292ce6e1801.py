"""
difference_of_squares.py: program to find the difference between the sum of
the squares and the square of the sums of the first N natural numbers.
"""


def square_of_sum(number):
    """
    Function to return the square of sum for a given sequence,
    ending at number.
        number: where the sequence of natural numbers should end.
    """
    number_lst = []
    for i in range(number):
        i += 1
        number_lst.append(i)
    total = sum(number_lst) ** 2
    return total


def sum_of_squares(number):
    """
    Function to return the sum of squared numbers for a given number.
        number: where the sequence of natural numbers should end.
    """
    number_lst = []
    for i in range(number):
        i += 1
        number_lst.append(i)
    total = sum([j**2 for j in number_lst])
    return total


def difference(number):
    """
    Find the difference of the square of sum and sum of squares
    for a given number.
        number: where the squence of natural numbers should end.
    """
    return square_of_sum(number) - sum_of_squares(number)

def square_of_sum(num):
    """Returns the square of the sum"""
    return sum(range(num + 1)) ** 2

def sum_of_squares(num):
    """Returns the sum of the squares"""
    return sum([i ** 2 for i in range(num+1)])

def difference(num):
    """Returns the difference of the square of the sum and the sum of the squares"""
    return square_of_sum(num) - sum_of_squares(num)

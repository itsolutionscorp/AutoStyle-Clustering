""" Return square of the sum of numbers 1 to n inclusive
"""
def square_of_sum(n):
    _sum = 0
    # Calculate sum
    for i in range(1,n+1):
        _sum += i
    # Square and return sum
    return _sum**2

""" Return the sum of squares of numbers 1 to n inclusive
"""
def sum_of_squares(n):
    _sum = 0
    for i in range(1,n+1):
        # Add square of i for 1<=i<=n to sum
        _sum += i**2
    return _sum

""" Return the difference between sum of squares of 1 to n
    and the square of the sum of 1 to n
"""
def difference(n):
    return abs(sum_of_squares(n) - square_of_sum(n))

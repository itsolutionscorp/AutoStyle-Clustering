def square_of_sum(value):
    """Returns the sum of the range 1 to given value"""
    return (((value*(value+1))//2)**2)

def sum_of_squares(value):
    """Returns the sum of the squares of numbers of the range from 1 to the given value"""
    return ((value*(value+1)*(2*value+1))//6)

def difference(value):
    """Returns the difference between the square of the sums and the sum of the squares of the given value"""
    return(square_of_sum(value)-sum_of_squares(value))

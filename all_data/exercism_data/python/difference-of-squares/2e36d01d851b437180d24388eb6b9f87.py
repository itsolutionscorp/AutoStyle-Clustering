def square_of_sum(upper):
    return sum(range(1, upper+1))**2

def sum_of_squares(upper):
    return sum([x**2 for x in range(1, upper+1)])

def difference(upper):
    return abs(square_of_sum(upper) - sum_of_squares(upper))

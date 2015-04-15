def square_of_sum(value):
    return sum(x for x in range(1,value+1))**2

def sum_of_squares(value):
    return sum(x**2 for x in range(1,value+1))

def difference(value):
    return abs(square_of_sum(value) - sum_of_squares(value))

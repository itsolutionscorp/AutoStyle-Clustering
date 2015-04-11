def square_of_sum(x):
    return sum(range(1,x+1))**2

def sum_of_squares(x):
    return sum(x**2 for x in range(1,x+1))

def difference(x):
    return square_of_sum(x) - sum_of_squares(x)

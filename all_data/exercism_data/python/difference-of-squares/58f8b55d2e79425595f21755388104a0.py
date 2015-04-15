def difference(value):
    return square_of_sum(value) - sum_of_squares(value)

def square_of_sum(value):
    return sum(range(1,value+1))**2 

def sum_of_squares(value):
    return sum(map(lambda v:v**2, range(1,value+1)))

import math
def square_of_sum(incoming):
    return sum(range(1, incoming +1)) ** 2
    
def sum_of_squares(incoming):
    return sum(x ** 2 for x in range(0,incoming + 1))
    
def difference(incoming):
    return int(math.fabs(square_of_sum(incoming) - sum_of_squares(incoming)))

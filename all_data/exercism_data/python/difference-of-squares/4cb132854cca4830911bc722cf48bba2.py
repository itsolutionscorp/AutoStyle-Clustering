def difference(m):
    return abs(sum_of_squares(m) - square_of_sum(m))
    
def square_of_sum(m):
    return sum([n for n in range(1, m + 1)]) ** 2
    
def sum_of_squares(m):
    return sum([n ** 2 for n in range(1, m + 1)])
    
    

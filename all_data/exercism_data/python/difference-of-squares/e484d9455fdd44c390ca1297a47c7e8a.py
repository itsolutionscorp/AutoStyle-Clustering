def square_of_sum(limit):
    return (sum([x for x in range(1,limit+1)]))**2
    
def sum_of_squares(limit):
    return sum([x**2 for x in range(1,limit+1)])
    
def difference(limit):
    return (sum([x for x in range(1,limit+1)]))**2 - sum([x**2 for x in range(1,limit+1)])

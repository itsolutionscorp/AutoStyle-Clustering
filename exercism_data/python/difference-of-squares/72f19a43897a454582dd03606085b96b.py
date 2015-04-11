def square_of_sum(lim):
    return (sum(range(lim+1)))**2

def sum_of_squares(lim):
    return sum([num**2 for num in range(lim+1)])

def difference(num):
    return abs(square_of_sum(num)-sum_of_squares(num))

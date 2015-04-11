def difference(i):
    return square_of_sum(i) - sum_of_squares(i)

def square_of_sum(i):
    return sum(j for j in range(1,i+1))**2

def sum_of_squares(i):
    return sum(j**2 for j in range(1,i+1))

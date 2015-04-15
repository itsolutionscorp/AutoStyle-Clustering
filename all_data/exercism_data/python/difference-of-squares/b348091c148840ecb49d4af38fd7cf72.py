# program to compute the difference between the sum of squares and the square of sums

def difference(number):
    return sum(range(0,number+1))**2-sum([x**2 for x in range(0,number+1)])
    
def square_of_sum(number):
    return sum(range(0,number+1))**2
def sum_of_squares(number):
    return sum([x**2 for x in range(0,number+1)])

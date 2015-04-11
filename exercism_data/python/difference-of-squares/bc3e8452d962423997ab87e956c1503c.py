# program to compute the difference between the sum of squares and the square of sums

def difference(number):
    return square_of_sum(number)-sum_of_squares(number)
def square_of_sum(number):
    return (number*(number+1)/2)**2
def sum_of_squares(number):
    return (2*number+1)*(number+1)*number/6

def difference_old(number):
    return sum(range(0,number+1))**2-sum([x**2 for x in range(0,number+1)])
    
def square_of_sum_old(number):
    return sum(range(0,number+1))**2
def sum_of_squares_old(number):
    return sum([x**2 for x in range(0,number+1)])

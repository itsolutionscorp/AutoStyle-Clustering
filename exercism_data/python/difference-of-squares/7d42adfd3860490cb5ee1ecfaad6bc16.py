#
# Returns the difference of the sum of the squares under a number 'n'
# and the square of the sum of the numbers under 'n'
#

def square_of_sum(n):
    return (n*(n+1)/2)**2

def sum_of_squares(n):
    return sum(i**2 for i in range(1, n+1))

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

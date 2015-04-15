# Return difference of square_of_sum and sum_of_squares
def difference(x):
    return square_of_sum(x) - sum_of_squares(x)
    
# Use mathematical induction to get sum then return the square of the result
def square_of_sum(x):
    return (x*(x+1)/2)**2
    
# Use sum of squares formula and return the result
def sum_of_squares(x):
    return (x*(x+1)*(x*2+1)/6)

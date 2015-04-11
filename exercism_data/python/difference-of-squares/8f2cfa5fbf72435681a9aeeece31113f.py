def square_of_sum(n):
    '''return the square of the sum(1...n)'''
    return(sum(range(n+1))**2)

def sum_of_squares(n):
    '''return the sum of all squares 1...n'''
    return(sum(i**2 for i in range(n+1)))

def difference(n):
    '''return the difference between the sum of squares and the square of the sum for 1...n'''
    return(abs(sum_of_squares(n)-square_of_sum(n)))

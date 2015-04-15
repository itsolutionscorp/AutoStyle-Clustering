def square_of_sum( N ):
    total = 0
    for ii in range( 1, N+1 ):
        total += ii
    return total**2

def sum_of_squares( N ):
    total = 0
    for ii in range( 1, N+1 ):
        total += ii**2
    return total

def difference( N ):
    return square_of_sum( N ) - sum_of_squares( N )

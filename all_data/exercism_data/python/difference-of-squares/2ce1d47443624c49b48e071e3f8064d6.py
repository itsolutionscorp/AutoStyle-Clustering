def square_of_sum( N ):
    return sum( range( 0, N+1 ) )**2

def sum_of_squares( N ):
    return sum( x**2 for x in range( 0, N+1 ) )

def difference( N ):
    return square_of_sum( N ) - sum_of_squares( N )

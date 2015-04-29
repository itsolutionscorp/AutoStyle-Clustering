def saddle_points(A):
    """Returns a set of saddle points of a matrix"""
    if A == []:
        return set( [] )
    A_ = [list(x) for x in zip(*A) ] #Take matrix transpose
    try:
        return set( [ (row,col)for col in range( len( A[0] ) ) 
                   for row in range( len(A) ) if 
                   A[row][col] == max( A[row] ) and
                   A_[col][row] == min(A_[col] ) ] )
    except IndexError:
        raise ValueError("irregular matrix")

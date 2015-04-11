def saddle_points(A):
    """Returns a set of saddle points of a matrix"""
    if A == []:
        return set( [] )
    if not is_matrix(A):
        raise ValueError("irregular matrix")

    A_ = [list(x) for x in zip(*A) ] #Take matrix transpose
    return set( [ (row,col)for col in range( len( A[0] ) ) 
               for row in range( len(A) ) if 
               A[row][col] == max( A[row] ) and
               A_[col][row] == min(A_[col] ) ] )

def is_matrix(matrix):
    if any( [len( matrix[n] ) != len( matrix[n+1] ) for 
            n in range(len(matrix) - 1) ] ):
        return False #Some column is larger than the next one
    else:
        return True

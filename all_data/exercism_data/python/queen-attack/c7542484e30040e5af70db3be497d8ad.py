def can_attack( W, B ):
    check_validity( W, B )
    return W[0] == B[0] or W[1] == B[1] or \
           W[0] - W[1] == B[0] - B[1]   or \
           W[0] + W[1] == B[0] + B[1]

def board( W, B ):
    check_validity( W, B )
    my_board = []
    for ii in range(8):
        my_board.append( ['_']*8 )
    my_board[W[0]][W[1]] = 'W'
    my_board[B[0]][B[1]] = 'B'
    return [ ''.join(row) for row in my_board ]

def check_validity( W, B ):
    if not( W[0] in range(8) and W[1] in range(8) and \
            B[0] in range(8) and B[1] in range(8) and \
            not W == B ):
        raise ValueError( 'Invalid input!' )

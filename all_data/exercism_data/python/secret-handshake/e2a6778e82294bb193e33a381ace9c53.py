from re import match

move_list = [ 'wink', 'double blink', 'close your eyes', 'jump' ]

def int_to_binary( N ):
    if N < 0:
        raise ValueError( "int_to_binary saw a negative" )
    b = ''
    while N > 0:
        b = str(N%2) + b
        N = N // 2
    return b

def binary_str_to_int( N ):
    n = 0
    for ii in range( len(N)-1, -1, -1 ):
        if N[ii] == '1':
            n += 2 ** (len(N)-ii-1)
    return n

def handshake( N ):
    N = read_input( N )
    moves = []
    for ii in range( 4, 0, -1 ):
        if N[ii] == "1":
            moves.append( move_list[4-ii] )
    if N[0] == "1":
        moves.reverse()
    return moves

def read_input( N ):
    if type( N ) is str:
        if match("^[0-1]*$",N):
            return "0" * ( 5-len(N) ) + N[0:min(len(N),5)]
        return "00000"
    elif type( N ) is int:
        if N > 0:
            return read_input( int_to_binary( N ) )
        return "00000"

def code( actions ):
    for ii in range( 2 ** (len(move_list)+1) ):
        if handshake( ii ) == actions:
            return int_to_binary( ii )
    return '0'

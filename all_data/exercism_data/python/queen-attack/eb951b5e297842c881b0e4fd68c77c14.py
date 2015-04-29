def board(white, black):
    check(white, black)
    blank = [list(row) for row in ['_'*8,]*8]
    blank[white[0]][white[1]] = 'W'
    blank[black[0]][black[1]] = 'B'
    return [''.join(row) for row in blank]
    
def can_attack(white, black):
    (a, b) = white
    (c, d) = black
    check(white, black)
    for n in xrange(1, 8):
        moves = [(a+n, b+n), (a+n, b-n), (a-n, b+n), (a-n, b-n)]
        if (c, d) == (a, n):
            return True
        elif (c, d) == (n, b):
            return True 
        elif (c, d) in moves:
            return True
    return False
    
def check(white, black):
    for i in [white[0], white[1], black[0], black[1]]:
        if i > 7:
            raise ValueError
            print 'Pieces are off-board'
    if white == black:
        raise ValueError
        print 'White and Black are in same position'
    return True

from re import match

def board( rows ):
    check_board( rows )

    new_board = []
    for ii in range(len(rows)):
        new_row = ''
        for jj in range(len(rows[0])):
            if not rows[ii][jj] == " ":
                new_row += rows[ii][jj]
            else:
                ct = 0
                for pr in neighbors( ii, jj, len(rows), len(rows[0]) ):
                    if rows[pr[0]][pr[1]] == '*':
                        ct += 1
                if ct == 0:
                    new_row += " "
                else:
                    new_row += str(ct)
        new_board.append( new_row )
    return new_board

def neighbors( r, c, Nrows, Ncols ):
    delta = {-1, 0, 1}
    return [ (r+d1, c+d2) for d1 in delta for d2 in delta
             if r+d1 in range(1,Nrows-1) and c+d2 in range(1,Ncols-1)
             and not (d1,d2) == (0,0)]

def check_board( rows ):
    if len(rows) < 2:
        raise ValueError( "Not enough rows!" )
    N = len(rows[0])
    if N < 2:
        raise ValueError( "Not enough columns!" )
    for row in rows:
        if not len(row) == N:
            raise ValueError( "Grid must be a rectangle" )

    #check first and last rows
    if not( rows[0][0] == "+" and rows[0][-1] == "+" and
            rows[-1][0] == "+" and rows[-1][-1] == "+" ):
        raise ValueError( "Need '+' in corners" )
    if not( match("^[-]*$", rows[0][1:N-1]) and
            match("^[-]*$", rows[-1][1:N-1]) ):
        raise ValueError( "Need '-' along the top and bottom!" )

    #check sides
    for row in rows[1:len(rows)-1]:
        if not( row[0] == "|" and row[-1] == "|" ):
            raise ValueError( "Need '|' along the sides!" )

    #check inside
    for row in rows[1:len(rows)-1]:
        if not match("^[ *]*$", row[1:N-1]):
            raise ValueError( "Need '*' or ' ' inside the box!" )

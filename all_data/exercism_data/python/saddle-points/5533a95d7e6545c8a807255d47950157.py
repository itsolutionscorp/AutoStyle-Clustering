def saddle_points( inp ):
    if len( inp ) == 0:
        return set()
    
    num_of_cols = len(inp[0])
    for row in inp:
        if len(row) != num_of_cols:
            raise ValueError( 'Rows do not all have same length!' )

    saddlePts = set()
    for ii in range( len(inp) ):
        for jj in range( num_of_cols ):
            if inp[ii][jj] >= max( inp[ii] ) and \
               inp[ii][jj] <= min( [ row[jj] for row in inp ] ):
                   saddlePts.add( (ii,jj) )
    return saddlePts

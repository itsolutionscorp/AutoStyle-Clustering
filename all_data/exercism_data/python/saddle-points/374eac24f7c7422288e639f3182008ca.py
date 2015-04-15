def saddle_points(inp):

    if not all(len(row) == max(map(len,inp)) for row in inp):
        raise ValueError('Irregular input.')
    
    # Get the sets of row maximal elements and col minimal elements.
    # The saddle points are the intersection of the two sets.
    rowmaxset = getrowmax(inp)
    colminset = set((i,j) for j,i in getrowmax(zip(*inp),min))

    return rowmaxset & colminset

def getrowmax(inp, cmpfn=max):
    '''Return the set of indexes of the max (or min if specified) elements of each row.'''
    return set((rownum, maxidx) for rownum,row in enumerate(inp) for maxidx,val in enumerate(row) if val == cmpfn(row))

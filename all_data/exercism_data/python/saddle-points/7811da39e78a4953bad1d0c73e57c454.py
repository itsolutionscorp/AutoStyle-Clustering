      
def saddle_points(inp):
    
    rows = inp
    
    if not rows:
        return set()
    
    # Raise ValueError if all rows are not the same length
    if not all([len(row) == len(rows[0]) for row in rows]):
        raise ValueError('Rows must be of equal length!')
            
    colN = len(rows[0])
    
    cols = []
    for i in xrange(colN):
        cols.append([row[i] for row in rows])

    saddles = []
    for i,row in enumerate(rows):
        highrow = max(row)
        
        for j in range(colN):
            if row[j] == highrow:
                col = cols[j]
                lowcol = min(col)
                if row[j] == lowcol:
                    saddles.append((i,j))
                    
    return set(saddles)

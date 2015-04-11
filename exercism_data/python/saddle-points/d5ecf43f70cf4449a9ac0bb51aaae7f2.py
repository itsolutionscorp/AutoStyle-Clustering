def saddle_points(input):
    if not len(input):
        return set()

    size = len(input[0])
    for row in input:
        if len(row) != size:
            raise( ValueError( 'irregular matrix' ) )

    min_points = set()

    for i, row in enumerate(input):
        max_val = max(row)
        for j, val in enumerate(row):
            if max_val == val:
                max_points.add( ( i, j ) );

    max_points = set()

    for j in range( len(input[0]) ):
        col = [ row[j] for row in input ]
        min_val = min(col)
        for i, val in enumerate(col):
            if min_val == val:
                min_points.add( ( i, j ) );

    return min_points & max_points

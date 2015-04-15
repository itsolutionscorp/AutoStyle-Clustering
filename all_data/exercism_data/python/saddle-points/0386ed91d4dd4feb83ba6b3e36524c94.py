from itertools import product

def saddle_points(rows):
    '''Returns a Set of saddle points in a given matrix'''

    if any(len(row) != len(rows[0]) for row in rows):
        raise ValueError('Invalid matrix')

    columns = list(zip(*rows))
    cords = list(product(range(len(rows)), range(len(columns))))

    return {(x, y) for x, y in cords
                   if rows[x][y] == max(rows[x]) and
                      rows[x][y] == min(columns[y])}

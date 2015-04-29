zero = [" _ ", "| |", "|_|", "   "]
one  = ["   ", "  |", "  |", "   "]
def grid(number):
    if int(number) == 1:
        return one
    elif int(number) == 0:
        return zero
    else:
        raise ValueError("Not implemented yet")

def number(grid):
    _validate(grid)
    if grid == zero:
        return '0'
    elif grid == one:
        return '1'
    else:
        return '?'

def _validate(grid):
    # validate number of rows
    if len(grid) != 4:
        raise ValueError("Grid should have 4 rows")
    # validate row length
    for row in grid:
        if len(row) != 3:
            raise ValueError("Invalid row length '%s'" %(row))

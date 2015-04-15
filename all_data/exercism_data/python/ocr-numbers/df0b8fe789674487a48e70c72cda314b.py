ocrs = [[" _ ", "| |", "|_|", "   "],
        ["   ", "  |", "  |", "   "]]

def grid(number):
    n = int(number)
    if n not in xrange(len(ocrs)):
        raise ValueError("Not implemented yet")
    return ocrs[n]

def number(grid):
    _validate(grid)
    for n, ocr in enumerate(ocrs):
        if grid == ocr:
            return str(n)
    return '?'

def _validate(grid):
    """ validate number of rows """
    if len(grid) != 4:
        raise ValueError("Grid should have 4 rows")

    """ validate row length """
    for row in grid:
        if len(row) != 3:
            raise ValueError("Invalid row length '%s'" %(row))

def number(grid):
    ROW0 = {' _ ': set(['0', '2', '3', '5', '6', '7', '8', '9']),
            '   ': set(['1', '4'])}
    ROW1 = {'  |': set(['1', '7']),
            ' _|': set(['2', '3']),
            '|_|': set(['4', '8', '9']),
            '|_ ': set(['5', '6']),
            '| |': set(['0'])}
    ROW2 = {'  |': set(['1', '4', '7']),
            ' _|': set(['3', '5', '9']),
            '|_|': set(['0', '6', '8']),
            '|_ ': set(['2'])}
    if not grid or len(grid) < 4 or\
       any(len(row) != len(grid[0]) for row in grid):
        raise ValueError('Invalid grid')
    number = ROW0.get(grid[0], set())
    number &= ROW1.get(grid[1], set())
    number &= ROW2.get(grid[2], set())
    if len(number) == 0 or grid[3] != '   ':
        return '?'
    return number.pop()


def grid(n):
    grids = {'0': [" _ ", "| |", "|_|", "   "],
             '1': ["   ", "  |", "  |", "   "]}
    if n not in grids:
        raise ValueError('Invalid number')
    return grids[n]

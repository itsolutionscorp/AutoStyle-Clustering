_NB_TO_GRID = {
    '0': [" _ ", "| |", "|_|", "   "],
    '1': ["   ", "  |", "  |", "   "],
}


def grid(number):
    if number not in _NB_TO_GRID.keys():
        raise ValueError
    else:
        return _NB_TO_GRID[number]


def number(grid):
    if set([len(i) for i in grid]) | set([len(grid)]) != set([3, 4]):
        raise ValueError
    elif grid not in _NB_TO_GRID.values():
        return '?'
    else:
        for nb, gridi in _NB_TO_GRID.iteritems():
            if (gridi == grid):
                return nb


## _  012
##|_| 345
##|_| 678

BINARY_SEGMENTS = [490, 288, 242, 434, 312, 410, 474, 290, 506, 442]
CHARACTERS = " _ |_||_|   "

def slices(iterable, n):
    return [iterable[start:start+n]
            for start in range(0, len(iterable) - n + 1, n)]
    
 
def grid(s):
    segments = BINARY_SEGMENTS[int(s)]
    return slices(''.join(char if segments & 2**power else ' '
                          for power, char in enumerate(CHARACTERS)), 3)

def number(grid):
    if ((len(grid) != 4) or
        (len(set(len(row) for row in grid)) != 1)):
        raise ValueError
    
    grid = ''.join(grid)
    if ((set(grid) - set(' |_')) or
        (grid[-3:] != '   ')):
        return '?'

    segments = sum(2**power
                   for (power, char), val in zip(enumerate(CHARACTERS), grid)
                   if char == val != ' ')
    try:
        return str(BINARY_SEGMENTS.index(segments))
    except ValueError:
        return '?'

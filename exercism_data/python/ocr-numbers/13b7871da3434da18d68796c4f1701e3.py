MASK = [   # chararacter bits:
    ' _ ', # -- 01 --
    '|_|', # 02 04 08
    '|_|', # 16 32 64
    '   '  # -- -- --
]

NUMBER_TO_HASH = {
    0: 123, 1: 72,  2: 61, 3: 109, 4: 78,
    5: 103, 6: 119, 7: 73, 8: 127, 9: 111
}

GRID_TO_NUMBER = { }

NUMBER_TO_GRID = { }

def number(grid):
    grid_key = _create_grid_key(grid)
    number = GRID_TO_NUMBER.get(grid_key, '?')
    return str(number)

def grid(number):
    try:
        return NUMBER_TO_GRID[int(number)]
    except (KeyError, ValueError):
        raise ValueError(
            "Invalid input: %s (expected number 0 - 9)" % number)

class InvalidGridError(ValueError): pass

def _create_grid_key(grid):
    if len(grid) != len(MASK):
        raise InvalidGridError("Grid height invalid")
    if any(len(grid_line) != len(mask_line)
           for grid_line, mask_line in zip(grid, MASK)):
        raise InvalidGridError("Grid width invalid")
    return "".join(grid)

def _create_grid_for_number(number):
    hash_val = NUMBER_TO_HASH[number]
    grid = []
    bit = -1
    for mask_line in MASK:
        grid_line = []
        for mask_char in mask_line:
            if mask_char == " ":
                grid_line.append(" ")
            else:
                bit += 1
                bit_val = 2 ** bit
                grid_char = mask_char if hash_val & bit_val else " "
                grid_line.append(grid_char)
        grid.append("".join(grid_line))
    return grid

for n in range(10):
    g = _create_grid_for_number(n)
    grid_key = _create_grid_key(g)
    GRID_TO_NUMBER[grid_key] = n
    NUMBER_TO_GRID[n] = g 

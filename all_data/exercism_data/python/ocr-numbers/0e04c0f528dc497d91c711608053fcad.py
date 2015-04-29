NUMBERS = ([' _ ', '| |', '|_|', '   '],
           ['   ', '  |', '  |', '   '],
           [' _ ', ' _|', '|_ ', '   '],
           [' _ ', ' _|', ' _|', '   '],
           ['   ', '|_|', '  |', '   '],
           [' _ ', '|_ ', ' _|', '   '],
           [' _ ', '|_ ', '|_|', '   '],
           [' _ ', '  |', '  |', '   '],
           [' _ ', '|_|', '|_|', '   '],
           [' _ ', '|_|', ' _|', '   '])  # it's garbled only to an human eye.


def number(grid):
    try:
        return str(NUMBERS.index(grid))
    except ValueError:  # not in grid ? catch that exception
        # check dimensions
        try:
            for row in range(4):
                for col in range(3):
                    grid[row][col]  # try to access this value to maybe raise an exception to catch
        except:
            raise ValueError("grid for OCR must be 3 x 4")
        return "?"  # that was the right size, but not recognized


def grid(n):
    return NUMBERS[int(n)]

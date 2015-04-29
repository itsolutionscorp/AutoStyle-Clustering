NUMBERS = [[" _ ",
            "| |",
            "|_|",
            "   "],
           ["   ",
            "  |",
            "  |",
            "   "]]


def number(grid):
    if len(grid) != 4 or any(len(row) != 3 for row in grid):
        raise ValueError("Grid malformed")
    else:
        try:
            return str(NUMBERS.index(grid))
        except ValueError:
            return '?'


def grid(number):
    try:
        return NUMBERS[int(number)]
    except IndexError:
        raise ValueError("Not a valid number {}".format(number))

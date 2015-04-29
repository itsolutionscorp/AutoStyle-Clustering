ZERO = [" _ ",
        "| |",
        "|_|",
        "   "]
ONE = ["   ",
       "  |",
       "  |",
       "   "]


def number(grid_):
    if len(grid_) != 4 or any(len(row) != 3 for row in grid_):
        raise ValueError("Invalid or irregular grid dimensions")
    if grid_ == ZERO:
        return '0'
    if grid_ == ONE:
        return '1'
    return '?'


def grid(number_):
    if number_ == '0':
        return ZERO
    if number_ == '1':
        return ONE
    raise ValueError("This is what the tests want!")

ZERO = [
    " _ ",
    "| |",
    "|_|",
    "   "
]

ONE = [
    "   ",
    "  |",
    "  |",
    "   "
]


def grid(number_value):
    if number_value == '1':
        return ONE
    elif number_value == '0':
        return ZERO
    else:
        raise ValueError

def number(grid_value):
    if grid_value == ONE:
        return '1'
    elif grid_value ==ZERO:
        return '0'
    else:
        if len(grid_value) != 4:
            raise ValueError
        for i in range(0,3):
            if len(grid_value[i]) != 3:
                raise ValueError
        return '?'

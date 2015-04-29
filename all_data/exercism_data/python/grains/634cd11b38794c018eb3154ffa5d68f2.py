__author__ = 'angelo'


def on_square(square):
    if square == 1:
        return 1
    else:
        return on_square(square - 1) * 2

def total_after(square):
    return sum([on_square(x) for x in range(1, square + 1)])

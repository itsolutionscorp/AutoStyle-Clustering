def on_square(square_number):
    return 2 ** (square_number - 1)


def total_after(square_number):
    return sum([2 ** n for n in range(square_number)])

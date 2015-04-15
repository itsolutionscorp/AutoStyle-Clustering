def on_square(square_number):
    return 2**(square_number - 1)


def total_after(square_number):
    return on_square(square_number + 1) - 1

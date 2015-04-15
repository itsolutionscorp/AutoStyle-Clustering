def on_square(square_number):
    binary_representation = "0b1" + "0" * (square_number - 1)
    return int(binary_representation, 2)


def total_after(squares):
    binary_representation = "0b" + "1" * squares
    return int(binary_representation, 2)

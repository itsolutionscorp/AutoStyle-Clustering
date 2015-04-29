def total_after(num_of_squares):
    return sum([on_square(grid_val) for grid_val in range(1, num_of_squares+1, 1)])


def on_square(square_num):
    return 2**(square_num-1)

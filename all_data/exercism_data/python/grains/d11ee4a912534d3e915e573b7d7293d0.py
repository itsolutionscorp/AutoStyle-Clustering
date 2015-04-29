def on_square(square_index):
    return 1 << (square_index - 1)

def total_after(square_index):
    return (1 << square_index) - 1

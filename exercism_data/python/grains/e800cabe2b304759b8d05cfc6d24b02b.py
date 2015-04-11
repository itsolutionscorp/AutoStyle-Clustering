def on_square(squares):
    return 2 ** (squares - 1)

def total_after(squares):
    total = 0
    for i in range(1, squares + 1):
        total += on_square(i)
    return total

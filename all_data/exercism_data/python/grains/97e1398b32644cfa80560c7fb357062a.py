# Grains

def on_square(square_num):
    return 2**(square_num - 1)


def total_after(square_num):
    return sum([2**x for x in range(square_num)])

'''exer grains'''

def on_square(square_num):
    '''1 grain on square 1, doubling on each square, return number of grains
    on the indicated square'''

    grains = 1
    for _ in range(square_num - 1):
        grains += grains
    ## below is also correct, but less readable, speed difference is negligible
    # if square_num > 1:
    #     grains = 2 ** (square_num - 1)

    return grains


def total_after(square_num):
    '''1 grain on square 1, doubling on each square, return number of grains
    on the board, including the indicated square'''

    grains = 0
    for this_square in range(square_num):
        grains += on_square(this_square + 1)

    return grains

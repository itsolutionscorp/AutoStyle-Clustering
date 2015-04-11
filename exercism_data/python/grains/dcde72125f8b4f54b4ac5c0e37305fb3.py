memoizing = {}

def on_square(square_num):
    return 2 ** (square_num-1)

def total_after(square_num):
    return memoizing.setdefault(square_num,
            sum(on_square(sq) for sq in range(1, square_num+1)))

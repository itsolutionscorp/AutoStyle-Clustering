# the optimized version is 10x faster (measured with timeit)


def on_square(index, readable=False):
    if readable:
        return on_square_readable(index)
    else:
        return on_square_optimized(index)


def on_square_optimized(index):
    return 2 ** (index - 1)


def on_square_readable(index):
    if index == 1:
        return 1
    return 2 * on_square_readable(index - 1)


def total_after(index, readable=False):
    return sum(on_square(i, readable) for i in range(1, index + 1))

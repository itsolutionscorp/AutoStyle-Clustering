def on_square(square):
    return 1 << square - 1


def total_after(square):
    return sum(on_square(i) for i in xrange(1, square + 1))

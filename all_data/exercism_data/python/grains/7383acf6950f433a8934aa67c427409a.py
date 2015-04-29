def on_square(num):
    return 1 << (num - 1)


def total_after(num):
    return sum((on_square(x) for x in range(1, num+1)))

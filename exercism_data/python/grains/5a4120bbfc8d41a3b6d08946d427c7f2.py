def on_square(num):
    # Same as bitwise shift
    return 2 ** (num - 1)

def total_after(num):
    return sum(on_square(n + 1) for n in range(num))

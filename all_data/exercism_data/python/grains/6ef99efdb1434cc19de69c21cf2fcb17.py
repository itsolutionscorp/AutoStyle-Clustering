def on_square(i):
    """
    on_square(int i) -> int

    Returns the number of grains if we double 1 (i-1) times.
    """

    return 2**(i-1)

def total_after(i):
    """
    on_square(int i) -> int

    Returns the number of grains on all squares up to square i if we
    double the number of grains each time and start with 1.
    """

    # 2**(i) = 2**(0) + 2**(1) + 2**(2) + ... 2**(i-1) + 1
    #
    # Informal proof: The amount numbers representable with i bits is:
    # the amount of numbers starting with 1, plus the amount of numbers
    # starting with 01, plus the amount of numbers starting with 001,
    # and so on.
    return 2**i - 1

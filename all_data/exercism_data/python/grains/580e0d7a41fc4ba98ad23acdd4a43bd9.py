def on_square(i):
    """
    on_square(int) -> int

    Returns the number of grains on the ith square if we place one grain in
    the first square and double the amount of grains at each new square.
    """
    return 2**(i-1)

def total_after(i):
    """
    on_square(int) -> int

    Returns the number of grains on all squares up to square i if we
    double the number of grains each time and start with 1.
    """

    # 2**(i) = 2**(0) + 2**(1) + 2**(2) + ... 2**(i-1) + 1
    #
    # Proof: 2**(i) = 2**(i-1) + 2**(i-1)
    #               = 2**(i-1) + 2**(i-2) + 2**(i-2)
    #               = ...
    #               = 2**(i-1) + 2**(i-2) + ... + 2**(i-i) + 1
    return 2**i - 1

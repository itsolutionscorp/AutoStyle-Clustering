def on_square(sq):
    if not 0 < sq < 65:
        raise ValueError("sq must be between 1 and 64")
    return 2**(sq-1)

def total_after(sq):
    if not 0 < sq < 65:
        raise ValueError("sq must be between 1 and 64")

    result = 1
    for _ in range(sq-1):
        result = (result << 1) | 1
    
    return result

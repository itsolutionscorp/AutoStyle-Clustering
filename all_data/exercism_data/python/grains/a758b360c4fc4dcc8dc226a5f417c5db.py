def on_square( n ):
    if n == 1:
        return 1
    return 2 << (n-2)

def total_after( n ):
    return (2 << (n-1)) - 1

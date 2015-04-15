def on_square( square ):
    return 2**(square-1)

def total_after( square ):
    binary = '1' * square
    return int(binary, 2)

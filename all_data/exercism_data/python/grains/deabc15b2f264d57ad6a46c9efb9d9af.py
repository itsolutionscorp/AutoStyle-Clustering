def on_square(square):
    return(2**(square-1))

def total_after(square):
    r = 0
    while square:
        r += on_square(square)
        square -= 1
    return(r)

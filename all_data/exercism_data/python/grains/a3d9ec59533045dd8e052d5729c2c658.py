""" Calculates how many grains are on a square in a chessboard, and the total grains up to that square """
def on_square(n):
    return 2 ** (n-1)

def total_after(n):
    if n == 0:
        return n
    else:
        return on_square(n) + total_after(n-1)

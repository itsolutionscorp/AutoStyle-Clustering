squares = [0]*64

def on_square(n):
    n -= 1
    if not squares[n]:
        squares[n] = 2**(n)
    return squares[n]

def total_after(n):
    for i in range(1, n+1):
        on_square(i)
    
    return sum(squares[:n])

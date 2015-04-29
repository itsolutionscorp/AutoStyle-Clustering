def on_square(n: int) -> int:
    return 2**(n-1)

def total_after(n: int) -> int:
    return sum(on_square(i) for i in range(1, n+1))

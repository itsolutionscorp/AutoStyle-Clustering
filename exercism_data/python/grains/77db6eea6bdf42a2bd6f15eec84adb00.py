def on_square(n):
    return 2 ** (n - 1)

def total_after(n):
    # 1 + 2 + 4 + ... + 2^(n-1) = 2^n - 1
    return 2 ** n - 1

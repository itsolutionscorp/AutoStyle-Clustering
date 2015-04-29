def on_square(n):
    return 2**(n-1)
    
def total_after(n):
    if n == 1:
        return 1
    return 2**n - 1

print on_square(1)

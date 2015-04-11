def on_square(n):
    if n == 1: return 1
    else: return on_square(n-1)*2

def total_after(n):
    s = [1]
    for i in range(1,n): s.append(s[-1]*2)
    return sum(s)

# slices function from exercism + os.sep + python + os.sep + series + os.sep + series.py
def slices(s,n):
    if len(s) < n or n == 0: raise ValueError
    return [[int(item) for item in s[i:i+n]] for i in range(len(s)-n+1)]

def largest_product(s,n):
    if n == 0: return 1
    return max(reduce(lambda x, y: x * y, items) for items in slices(s,n))

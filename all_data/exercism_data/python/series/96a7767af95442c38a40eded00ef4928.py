def slices(s,n):
    if len(s) < n or n == 0: raise ValueError
    return [[int(item) for item in s[i:i+n]] for i in range(len(s)-n+1)]

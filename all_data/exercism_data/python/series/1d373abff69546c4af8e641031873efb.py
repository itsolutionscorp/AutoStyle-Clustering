#now using nest list comprehension!

def slices(s,n):
    if n > len(s):
        raise ValueError("Too Long of a Slice")
    elif n < 1:
        raise ValueError('Looking for the Null-Slice?')
    else:

        return [[int(j) for j in s[i:i+n]] for i in range(len(s)-n+1)]

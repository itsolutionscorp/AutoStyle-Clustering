def slices(x, n):
    x = [int(i) for i in x]

    out = [x[a:a+n] for a in range(len(x)-n+1)]

    if not out or not n:
        raise ValueError
    else:
        return out

def mult(nums):
    out = 1

    for num in nums:
        out *= num

    return out

largest_product = lambda x, n: 1 if not n else max(mult(y) for y in slices(x, n))

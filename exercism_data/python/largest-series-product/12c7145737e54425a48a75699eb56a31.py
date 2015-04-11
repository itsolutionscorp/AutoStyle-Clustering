def largest_product(digitstr, n):
    if n==0:
        return 1
    return max(product(digits) for digits in slices(digitstr, n))

def product(argv):
    acc = 1
    for arg in argv:
        acc *= arg
    return acc

def slices(digitstr, n):
    l = len(digitstr)
    if n<1 or n>l:
        raise ValueError
    digits = [int(d) for d in digitstr]
    return [digits[i:i+n] for i in range(l+1-n)]
        

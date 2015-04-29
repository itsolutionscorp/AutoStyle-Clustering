def slices(digitstr, n):
    l = len(digitstr)
    if n<1 or n>l:
        raise ValueError
    digits = [int(d) for d in digitstr]
    return [digits[i:i+n] for i in range(l+1-n)]

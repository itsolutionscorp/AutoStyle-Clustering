from operator import mul

def largest_product(dig, slen):
    if not dig:
        return 1
    return max([reduce(mul, [d for d in s]) for s in slices(dig, slen)])

def slices(dig, slen):
    dlen = len(dig)

    if slen > dlen or slen < 0:
        raise ValueError("DANGER, WILL ROBINSON")

    return [[ord(x)-ord('0') for x in dig][i:i+slen] for i in range(0, dlen-slen+1)]

def slices(dig, slen):
    dlen = len(dig)
    if slen > dlen or slen < 1:
        raise ValueError("DANGER, WILL ROBINSON")
    return [[ord(x)-ord('0') for x in dig][i:i+slen] for i in range(0, dlen-slen+1)]

def parse_binary(bstr):
    blist = list(bstr)
    dnum = 0
    n = 0
    while blist:
        cur_dig = blist.pop()
        if cur_dig == '1':
            dnum += 2**n
        elif cur_dig != '0':
            raise ValueError('Binary string must contain only 0 and 1')
        n += 1
    return dnum

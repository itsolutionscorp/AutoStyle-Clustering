def parse_binary(sn):
    n = 0
    for i in range(len(sn)):
        if sn[i] == '1':
            n += 2 ** (len(sn) - i - 1)
        elif sn[i] != '0':
            raise ValueError('Not a binary number!')
    return n

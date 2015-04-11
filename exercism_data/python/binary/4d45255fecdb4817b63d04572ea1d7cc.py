def parse_binary(st):
    n = 0
    p = 0

    for c in st[::-1]:
        if c == '1':
            n += 2 ** p
        elif c != '0':
            raise ValueError('%c not a binary digit.' % (c))
        p += 1

    return n

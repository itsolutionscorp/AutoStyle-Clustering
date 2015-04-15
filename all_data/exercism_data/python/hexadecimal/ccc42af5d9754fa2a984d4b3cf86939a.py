HEX = '0123456789abcdef'

def hexa(s):
    n = 0

    for c in s:
        pos = HEX.find(c.lower())
        if pos < 0:
            raise ValueError('"%c" is not a hexidecimal charactor.' % (c))
        n = n * 16 + pos

    return n

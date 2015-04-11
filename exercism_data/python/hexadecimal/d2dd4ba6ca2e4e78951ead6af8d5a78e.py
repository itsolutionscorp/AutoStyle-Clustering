hx = '0123456789ABCDEF'

def hexa(num):
    n = 0
    for i in num:
        position = hx.find(i.upper())
        if position < 0:
            raise ValueError('"{}" is not a hexidecimal charactor.'.format(i))
        n = n * 16 + position
    return n

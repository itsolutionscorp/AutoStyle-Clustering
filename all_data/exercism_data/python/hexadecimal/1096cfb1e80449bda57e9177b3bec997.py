hexdigits = '0123456789abcdef'

def hexa(h):
    h = h.lower()

    decimal = 0
    power = 1

    for i in reversed(h):
        try: i = hexdigits.index(i)
        except: raise ValueError()

        decimal += i * power
        power *= 16

    return decimal

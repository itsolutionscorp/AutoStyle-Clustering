HEX = {'0': 0,  '1': 1,  '2': 2,  '3': 3,
       '4': 4,  '5': 5,  '6': 6,  '7': 7,
       '8': 8,  '9': 9,  'a': 10, 'b': 11,
       'c': 12, 'd': 13, 'e': 14, 'f': 15}

def hexa(hex_d):
    try:
        return sum(HEX[decimal.lower()] * 16 ** (len(hex_d) - n)
                for n, decimal in enumerate(hex_d, 1))

    except KeyError:
        raise ValueError('Invalid Hex')

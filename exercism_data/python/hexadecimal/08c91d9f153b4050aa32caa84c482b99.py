HEXS = {'a': 10, 'b':11, 'c':12, 'd':13, 'e':14, 'f':15}

def hexa(num_str):
    return sum([conv(x)*(16**i) for i,x in enumerate(num_str[::-1])])

def conv(num):
    if num in '0123456789':
        return int(num)
    try:
        return HEXS[num.lower()]
    except:
        raise ValueError("Not a valid Hexadecimal number string")

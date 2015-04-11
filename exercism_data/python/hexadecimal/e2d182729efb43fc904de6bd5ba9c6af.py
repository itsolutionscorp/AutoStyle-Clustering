def hexa(hex_str):
    num = 0
    for n, h in enumerate(reversed(hex_str)):
        if str.isdigit(h):
            num += int(h) * 16**n
        elif h.lower() in 'abcedf':
            num += (ord(h.lower()) - 87) * 16**n
        else:
            raise ValueError("hexadecimal string must contain only 0-9 and a-f")
    return num

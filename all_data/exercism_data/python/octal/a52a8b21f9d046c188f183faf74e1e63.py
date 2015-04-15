def parse_octal(octal):
    ret = 0
    for i, digit in enumerate(reversed(octal)):
        digit = int(digit)
        if digit > 7:
            raise ValueError('Invalid octal string')
        ret += 8 ** i * digit
    return ret

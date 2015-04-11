
HEX_CHARS = '0123456789abcdef'

def hexa(input):
    return sum(16**power * HEX_CHARS.index(char)
               for power, char in enumerate(reversed(input.lower())))

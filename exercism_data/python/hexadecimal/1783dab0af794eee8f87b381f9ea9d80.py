import string

hex_chars = string.digits+string.letters[:6]

def hexa(hex):
    sum = 0
    for i, hexc in enumerate(list(hex.lower())[::-1]):
        sum += pow(2, i * 4) * hex_chars.index(hexc)

    return sum

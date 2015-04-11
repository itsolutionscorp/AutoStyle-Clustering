import re

def parse_binary(binary):
    if re.search(r"[^01]", binary) is not None:
        raise ValueError

    inverse = binary[::-1]
    decimal = 0
    exponent = 0

    for digit in inverse:
        if digit == "1":
            decimal += pow(2, exponent)
        exponent += 1

    return decimal

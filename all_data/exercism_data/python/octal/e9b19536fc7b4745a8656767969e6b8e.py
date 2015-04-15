def parse_octal_cheat(n):
    return int(n, base=8)


def parse_octal(n):
    out = 0
    coefficient = 1
    for digit in reversed(n):
        i = int(digit)
        if i >= 8:
            raise ValueError

        out += coefficient * i
        coefficient *= 8

    return out

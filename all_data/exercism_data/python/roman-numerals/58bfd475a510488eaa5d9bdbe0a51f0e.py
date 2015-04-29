numerals = [(3, "M", None, None),
            (2, "C", "D", "M"),
            (1, "X", "L", "C"),
            (0, "I", "V", "X")]


def numeral(arabic):
    result = ""
    for power, unit, mid, up in numerals:
        result += convert(arabic // 10**power, unit, mid, up)
        arabic = arabic % 10**power
    return result


def convert(n, unit, mid, up):
    if n < 4:
        return unit * n
    if n == 4:
        return unit + mid
    if 4 < n < 9:
        return mid + unit * (n-5)
    if n == 9:
        return unit + up

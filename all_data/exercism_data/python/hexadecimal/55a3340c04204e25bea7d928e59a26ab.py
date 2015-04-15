CONVERSIONS = {
    "a": 10,
    "b": 11,
    "c": 12,
    "d": 13,
    "e": 14,
    "f": 15
}

def hexa(h):
    return sum([__normalize(digit) * 16 ** i for i, digit in enumerate(reversed(h.lower()))])

def __normalize(digit):
    return int(CONVERSIONS.get(digit, digit))

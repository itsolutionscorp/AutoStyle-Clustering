ORDERS = [
    (1000, "M"),
    (100, ("M", "D", "C")),
    (10, ("C", "L", "X")),
    (1, ("X", "V", "I"))
]

DIGIT_FORMATS = {
    0: "",
    1: "{2}",
    2: "{2}{2}",
    3: "{2}{2}{2}",
    4: "{2}{1}",
    5: "{1}",
    6: "{1}{2}",
    7: "{1}{2}{2}",
    8: "{1}{2}{2}{2}",
    9: "{2}{0}",
}

def _convert_digit_to_numerals(digit, numerals):
    if not isinstance(numerals, tuple): # 1000/M
        return numerals * digit
    return DIGIT_FORMATS[digit].format(*numerals)

def numeral(n):
    digits = {}
    for order, _ in ORDERS:
        digits[order], n = n // order, n % order
    return ''.join(_convert_digit_to_numerals(digits[order], numerals)
                    for order, numerals in ORDERS)

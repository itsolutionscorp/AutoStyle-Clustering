ROMAN_NUMERALS = {
    1: "I",
    4: "IV",
    5: "V",
    9: "IX",
    10: "X",
    40: "XL",
    50: "L",
    90: "XC",
    100: "C",
    400: "CD",
    500: "D",
    900: "CM",
    1000: "M",
}


def numeral(num):
    numerals = []
    for k in sorted(ROMAN_NUMERALS.keys(), reverse=True):
        count = num//k
        num -= count*k
        numerals.extend(count*ROMAN_NUMERALS[k])
    return ''.join(numerals)

ROMAN_NUMERALS = {
    1: 'I',
    5: 'V',
    10: 'X',
    50: 'L',
    100: 'C',
    500: 'D',
    1000: 'M'
}


def convert(digit, multiplier):
    if digit == 0:
        return ''
    if digit < 4:
        return "".join([ROMAN_NUMERALS[multiplier] for x in range(1, digit + 1)])
    if digit == 4:
        return ROMAN_NUMERALS[multiplier] + ROMAN_NUMERALS[5 * multiplier]
    if digit < 9:
        return ROMAN_NUMERALS[5 * multiplier] + "".join([ROMAN_NUMERALS[multiplier] for x in range(1, digit - 5 + 1)])
    else:
        return ROMAN_NUMERALS[multiplier] + ROMAN_NUMERALS[10 * multiplier]


def numeral(arabic):
    digits = list(str(arabic))
    num_digits = len(digits)
    converted = ''
    for index, digit in enumerate(digits):
        converted += convert(int(digit), 10 ** (num_digits - index - 1))

    return converted

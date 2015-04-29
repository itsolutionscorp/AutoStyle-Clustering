ARABIC_TO_ROMAN = (
    (1000, 'M'),
    (900, 'CM'),
    (500, 'D'),
    (400, 'CD'),
    (100, 'C'),
    (90, 'XC'),
    (50, 'L'),
    (40, 'XL'),
    (10, 'X'),
    (9, 'IX'),
    (5, 'V'),
    (4, 'IV'),
    (1, 'I'),
)

def _closest_digit(value):
    for arabic, roman in ARABIC_TO_ROMAN:
        if arabic <= value:
            return arabic, roman

def numeral(value):
    letters = []
    while value > 0:
        arabic, roman = _closest_digit(value)
        letters.append(roman)
        value -= arabic
    return ''.join(letters)

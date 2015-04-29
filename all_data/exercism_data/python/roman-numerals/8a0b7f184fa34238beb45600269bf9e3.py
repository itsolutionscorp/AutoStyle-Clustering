NUMERAL_PARTS = (
    (1000, 'M'),
    (900,  'CM'),
    (500,  'D'),
    (400,  'CD'),
    (100,  'C'),
    (90,   'XC'),
    (50,   'L'),
    (40,   'XL'),
    (10,   'X'),
    (9,    'IX'),
    (5,    'V'),
    (4,    'IV'),
    (1,    'I')
)


def numeral(number):
    """Return the number represented as a roman numeral"""
    if not 1 <= number <= 3999:
        raise ValueError(
            "can't represent {} as a roman numeral".format(number))
    result = ''
    for value, representation in NUMERAL_PARTS:
        while number >= value:
            result += representation
            number -= value
    return result

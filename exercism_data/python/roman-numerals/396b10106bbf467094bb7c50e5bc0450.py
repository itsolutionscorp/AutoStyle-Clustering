from collections import OrderedDict

to_roman = OrderedDict([
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
    (1, 'I')])

def numeral(current: int) -> str:
    result = []
    for arabic, roman in to_roman.items():
        while arabic <= current:
            current -= arabic
            result.append(roman)
    return ''.join(result)

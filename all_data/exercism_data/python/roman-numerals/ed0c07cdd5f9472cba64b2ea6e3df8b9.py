_numerals = (
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


def numeral(arabic):
    roman = ''
    for arab, rom in _numerals:
        while arabic >= arab:
            roman += rom
            arabic -= arab

        if arabic <= 0:
            break

    return roman

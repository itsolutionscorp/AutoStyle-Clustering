def unpack_number(number):
    number = [int(n) for n in str(number)]
    number.reverse()

    factor = 1
    unpacked_number = []
    for n in number:
        n = n * factor
        factor *= 10
        unpacked_number.insert(0, n)
    return [n for n in unpacked_number if n != 0]


def numeral(number):
    """ Convert an integer to a Roman numeral. """

    numerals = {
        1000: 'M',
        2000: 'MM',
        3000: 'MMM',
        900: 'CM',
        800: 'DCC',
        700: 'DCC',
        600: 'DC',
        500: 'D',
        400: 'CD',
        300: 'CCC',
        200: 'CC',
        100: 'C',
        90: 'XC',
        80: 'LXXX',
        70: 'LXX',
        60: 'LX',
        50: 'L',
        40: 'XL',
        30: 'XXX',
        20: 'XX',
        10: 'X',
        9: 'IX',
        8: 'VIII',
        7: 'VII',
        6: 'VI',
        5: 'V',
        4: 'IV',
        3: 'III',
        2: 'II',
        1: 'I'
    }

    final_numeral = ''
    unpacked_number = unpack_number(number)

    for n in unpacked_number:
        final_numeral = final_numeral + numerals[n]

    return final_numeral

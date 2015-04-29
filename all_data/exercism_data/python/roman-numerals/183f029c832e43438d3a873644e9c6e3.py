__author__ = 'agupt15'

import math

ROMAN_UNIT_MAP = {1: 'I', 2: 'II', 3: 'III', 4: 'IV', 5: 'V', 6: 'VI', 7: 'VII', 8: 'VIII', 9: 'IX'}

ROMAN_MAP = {'X': (range(9, 40), 10), 'L': (range(40, 90), 50), 'C': (range(90, 400), 100), 'D': (range(400, 900), 500),
             'M': (range(900, 3001), 1000)}

ROMAN_VALUE_MAP = {value[1]: key for key, value in ROMAN_MAP.items()}


def numeral(arabic):
    if arabic > 3000:
        raise ValueError('Too high number for conversion')
    str_arabic = str(arabic)

    roman_numeral = ['' for _ in str_arabic]

    for i, digit in enumerate(str_arabic):
        multiplier = math.pow(10, len(str_arabic) - i - 1)
        value = int(digit) * multiplier
        if value == 0:
            continue
        if i == len(str_arabic) - 1:
            roman_numeral[i] = ROMAN_UNIT_MAP[value]
        else:
            for central_representation, (rng, central_value) in ROMAN_MAP.items():
                if value in rng:
                    # I could not repeat just by multiplying. say,
                    # character * times was giving error.
                    def repeat(char, count):
                        return ''.join([char for _ in range(0, int(count))])

                    if value == central_value:
                        roman_numeral[i] = central_representation
                    else:
                        x = central_representation
                        distance_from_central = abs(value - central_value)
                        times = distance_from_central / multiplier
                        character = ROMAN_VALUE_MAP[multiplier]
                        tmp = repeat(character, times)
                        if value > central_value:
                            x += tmp
                            roman_numeral[i] = x
                        else:
                            tmp += x
                            roman_numeral[i] = tmp

    return ''.join(roman_numeral)

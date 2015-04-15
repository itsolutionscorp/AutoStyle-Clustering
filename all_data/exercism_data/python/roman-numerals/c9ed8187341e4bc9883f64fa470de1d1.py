__author__ = 'emiller42'

import math


def numeral(arabic):

    num_dict = {1000: 'M',
                900: 'CM',
                500: 'D',
                400: 'CD',
                100: 'C',
                90: 'XC',
                50: 'L',
                40: 'XL',
                10: 'X',
                9: 'IX',
                5: 'V',
                4: 'IV',
                1: 'I'}

    roman = ""
    remainder = arabic

    for i in range(0, int(math.log10(remainder)+1)):
        for num in sorted(num_dict, reverse=True):
            while remainder >= num:
                roman += num_dict[num]
                remainder -= num

    return roman

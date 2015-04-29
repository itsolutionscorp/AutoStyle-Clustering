#!/usr/bin/env python

ROMAN_DIGITS = {1000: 'M',
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

class RomanNumeral:
    def __init__(self, number):
        self.number = number
    
    def __str__(self):
        roman = ""
        value = self.number
        while value > 0:
            for rd_value in reversed(sorted(ROMAN_DIGITS.keys())):
                if rd_value <= value:
                    roman += ROMAN_DIGITS[rd_value]
                    value -= rd_value
                    break
        return roman

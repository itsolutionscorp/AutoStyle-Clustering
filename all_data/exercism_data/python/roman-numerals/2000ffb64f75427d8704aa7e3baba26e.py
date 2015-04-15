from math import log10

ones = {0: "", 1: "I", 2: "II", 3: "III", 4: "IV", 5: "V", 6: "VI", 7: "VII", 8: "VIII", 9: "IX"}
tens = {0: "", 1: "X", 2: "XX", 3: "XXX", 4: "XL", 5: "L", 6: "LX", 7: "LXX", 8: "LXXX", 9: "XC"}
hundreds = {0: "", 1: "C", 2: "CC", 3: "CCC", 4: "CD", 5: "D", 6: "DC", 7: "DCC", 8: "DCCC", 9: "CM"}
thousands = {0: "", 1: "M", 2: "MM", 3: "MMM"}
numerals_for_exponent = {0: ones, 1: tens, 2: hundreds, 3: thousands}

def _numeral_components(number):
    for exponent in reversed(range(int(log10(number)) + 1)):
        digit = number / (10**exponent)
        if digit:
            yield numerals_for_exponent[exponent][digit]
            number %= 10**exponent

def numeral(number):
    return "".join(_numeral_components(number))

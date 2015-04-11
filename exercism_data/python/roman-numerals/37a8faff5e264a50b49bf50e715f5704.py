from collections import OrderedDict

def numeral(number):    
    roman = ''
    roman_symbols =  OrderedDict(((1000, 'M'), (900, 'CM'), (500, 'D'), (400, 'CD'), (100, 'C'), (90, 'XC'), (50, 'L'), (40, 'XL'), (10, 'X'), (9, 'IX'), (5, 'V'), (4, 'IV'), (1, 'I')))
    for value, symbol in roman_symbols.items():
        while number >= value:
            roman += symbol
            number -= value     
    return roman

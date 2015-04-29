#!/usr/bin/env python

numerals = [
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
    (1, 'I')
]

def numeral(n):
    validate_input(n)
    roman = ""
    while n:
        n, roman = breakdown(n, roman)
    return roman

def breakdown(n, roman):
    for a, b in numerals:
        if n >= a:
            roman += b
            n -= a
            return n, roman
    
def validate_input(n):
    if not isinstance(n, int):
        return ValueError("n must be an integer, {} received".format(type(n)))

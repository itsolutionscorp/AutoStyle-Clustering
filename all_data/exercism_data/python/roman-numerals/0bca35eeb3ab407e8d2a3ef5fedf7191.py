#!/usr/bin/env python3
numerals = {
    'I': 1,
    'IV': 4,
    'V': 5,
    'IX': 9,
    'X': 10,
    'XL': 40,
    'L': 50,
    'XC': 90,
    'C': 100,
    'CD': 400,
    'D': 500,
    'CM': 900,
    'M': 1000
}

numerals = {v: k for k, v in numerals.items()}

def numeral(arabic):
    numeral = []
    for i in sorted(numerals.keys())[::-1]:
        if arabic // i > 0:
            numeral.append(numerals[i] * (arabic // i))
            arabic = arabic % i
    return ''.join(numeral)

from math import floor, log10

numerals = {
    1: 'I',
    4: 'IV',
    5: 'V',
    9: 'IX',
    10: 'X',
    40: 'XL',
    50: 'L',
    90: 'XC',
    100: 'C',
    400: 'CD',
    500: 'D',
    900: 'CM',
    1000: 'M'
}


def roman(number, power):
    if not number:
        return ""
    if number in numerals:
        return numerals.get(number)
    return roman(number-power, power) + numerals.get(power)


def numeral(number):
    next_number = number
    num_len = int(floor(log10(number)))
    result = ''
    for num in range(num_len, -1, -1):
        power = 10 ** num
        remainder = number % power
        result += roman(next_number - remainder, power)
        next_number = remainder
    return result

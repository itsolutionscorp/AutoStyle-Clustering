arabic_numbers = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
roman_numerals = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]

def _numeral_components(number):
    for arabic_number, roman_numeral in zip(arabic_numbers, roman_numerals):
        while number >= arabic_number:
            yield roman_numeral
            number -= arabic_number

def numeral(number):
    return "".join(_numeral_components(number))

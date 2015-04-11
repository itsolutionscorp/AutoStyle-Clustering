unique_numbers = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
unique_numerals = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]
number_to_numeral = dict(zip(unique_numbers, unique_numerals))

def _numeral_components(number):
    for n in unique_numbers:
        while number >= n:
            yield number_to_numeral[n]
            number -= n

def numeral(number):
    return "".join(_numeral_components(number))

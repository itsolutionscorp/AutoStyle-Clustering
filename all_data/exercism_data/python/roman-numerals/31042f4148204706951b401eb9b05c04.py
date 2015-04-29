MAP = {1000: 'M', 500: 'D', 100: 'C', 50:'L', 10:'X', 5:'V', 1:'I',
        900: 'CM', 400:'CD', 90: 'XC', 40: 'XL', 9: 'IX', 4: 'IV'}  # Should probably have subtractive notation
                                                                    # be calculated instead of hard-coded.

def numeral(arabic):
    roman = ''
    for key in sorted(MAP.keys(), reverse=True):
        repeats = int(arabic / key)
        roman += MAP.get(key) * repeats
        arabic = arabic % key

    return roman

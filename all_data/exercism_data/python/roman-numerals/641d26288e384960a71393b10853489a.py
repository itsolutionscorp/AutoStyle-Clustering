ROMAN_NUMERALS = {
            1: 'I',
            5: 'V',
            10: 'X',
            50: 'L',
            100: 'C',
            500: 'D',
            1000: 'M'
            }

def numeral(arabic):
    result = ""

    # Base case
    if (arabic <= 0):
        return result

    for key, value in sorted(ROMAN_NUMERALS.items(), reverse = True):
        if (arabic >= key):
            if (str(arabic)[0] == '9'):
                arabic -= key * 2 - key / 5
                result += ROMAN_NUMERALS[key / 5] + ROMAN_NUMERALS[key * 2]
            elif (str(arabic)[0] == '4'):
                arabic -= key * 4
                result += value + ROMAN_NUMERALS[key * 5]
            else:
                arabic -= key
                result += value
            return result + numeral(arabic)

symbols={1: 'IVX', 10: 'XLC', 100: 'CDM'}

def numeral(arabic):
    roman = ""

    if arabic >= 1000:
        digit = arabic / 1000
        roman += 'M' * digit
        arabic -= 1000* digit

    if arabic >= 100:
        digit = arabic / 100
        roman += convertdigittoroman(digit, 100)
        arabic -= 100 * digit

    if arabic >= 10:
        digit = arabic / 10
        roman += convertdigittoroman(digit, 10)
        arabic -= 10 * digit

    if arabic > 0:
        roman += convertdigittoroman(arabic, 1)

    return roman


def convertdigittoroman(digit, symbidx):
    roman = ""
    chars = symbols[symbidx]
    if digit == 9:
        roman += chars[0] + chars[2]
    else:
        if digit >= 5:
            roman += chars[1]
            digit -= 5
        if digit == 4:
            roman += chars[0:2]
        else:
            roman += chars[0] * digit
    return roman

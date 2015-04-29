numerals = 'IVXLCDM??'

def numeral(arabic):
    roman = ""
    power = 3
    while arabic >= 1:
        digit = arabic // (10**power)
        roman += convertdigittoroman(digit, numerals[2*power:2*power+3])
        arabic -= digit * 10**power
        power -= 1
    return roman


def convertdigittoroman(digit, symbs):
    roman = ""
    if digit==0:
        return ''
    if digit == 9:
        roman += symbs[0] + symbs[2]
    else:
        if digit >= 5:
            roman += symbs[1] + symbs[0] * (digit - 5)
        elif digit == 4:
            roman += symbs[0:2]
        else:
            roman += symbs[0] * digit
    return roman

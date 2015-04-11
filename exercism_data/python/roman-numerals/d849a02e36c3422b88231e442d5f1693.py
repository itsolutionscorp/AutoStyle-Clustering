def numeral(arabic):
    romannum = ''
    if arabic >= 1000:
        romannum += convertDigit(arabic/1000, 'M', '', '')
        arabic %= 1000
    if arabic >= 100:
        romannum += convertDigit(arabic/100, 'C', 'D', 'M')
        arabic %= 100
    if arabic >= 10:
        romannum += convertDigit(arabic/10, 'X', 'L', 'C')
        arabic %= 10
    romannum += convertDigit(arabic, 'I', 'V', 'X')
    return romannum

def convertDigit(digit, onesLetter, fivesLetter, tensLetter):
    if digit <= 3:
        return digit*onesLetter
    elif digit == 4:
        return onesLetter + fivesLetter
    elif digit <= 8:
        return fivesLetter + (digit-5)*onesLetter
    else:
        return (10-digit)*onesLetter + tensLetter

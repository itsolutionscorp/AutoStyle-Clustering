def numeral(arabic):
    roman = []
    arab = arabic
    while arab > 0:
        if arab >= 1000:
            roman.append('M')
            arab -= 1000
        elif arab >= 900:
            roman.append('CM')
            arab -= 900
        elif arab >= 500:
            roman.append('D')
            arab -= 500
        elif arab >= 400:
            roman.append('CD')
            arab -= 400
        elif arab >= 100:
            roman.append('C')
            arab -= 100
        elif arab >= 90:
            roman.append('XC')
            arab -= 90
        elif arab >= 50:
            roman.append('L')
            arab -= 50
        elif arab >= 40:
            roman.append('XL')
            arab -= 40
        elif arab >= 10:
            roman.append('X')
            arab -= 10
        elif arab >= 9:
            roman.append('IX')
            arab -= 9
        elif arab >= 5:
            roman.append('V')
            arab -= 5
        elif arab >= 4:
            roman.append('IV')
            arab -= 4
        elif roman >= 1:
            roman.append('I')
            arab -= 1
        else:
            arab = 0
    return ''.join(roman)

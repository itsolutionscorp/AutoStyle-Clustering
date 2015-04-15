def numeral(arabic):
    numeral = ''
    while arabic >= 1000:
        numeral += 'M'
        arabic -= 1000
    if arabic >= 900:
        numeral += 'CM'
        arabic -= 900
    elif arabic >= 500:
        numeral += 'D'
        arabic -= 500
    elif arabic >= 400:
        numeral += 'CD'
        arabic -= 400
    while arabic >= 100:
        numeral += 'C'
        arabic -= 100
    if arabic >= 90:
        numeral += 'XC'
        arabic -= 90
    elif arabic >= 50:
        numeral += 'L'
        arabic -= 50
    elif arabic >= 40:
        numeral += 'XL'
        arabic -= 40
    while arabic >= 10:
        numeral += 'X'
        arabic -= 10
    if arabic == 9:
        numeral += 'IX'
        arabic -= 9
    elif arabic >= 5:
        numeral += 'V'
        arabic -= 5
    elif arabic == 4:
        numeral += 'IV'
        arabic -= 4
    while arabic >= 1:
        numeral += 'I'
        arabic -= 1
    return numeral

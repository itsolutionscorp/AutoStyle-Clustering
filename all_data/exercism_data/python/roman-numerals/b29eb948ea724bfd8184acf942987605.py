def numeral(number):
    roman = ''
    while number > 0:
        if number >= 1000:
            roman += 'M'
            number -= 1000
        elif number >= 900:
            roman += 'CM'
            number -= 900
        elif number >= 500:
            roman += 'D'
            number -= 500
        elif number >= 400:
            roman += 'CD'
            number -= 400
        elif number >= 100:
            roman += 'C'
            number -= 100
        elif number >= 90:
            roman += 'XC'
            number -= 90
        elif number >= 50:
            roman += 'L'
            number -= 50
        elif number >= 40:
            roman += 'XL'
            number -= 40
        elif number >= 10:
            roman += 'X'
            number -= 10
        elif number >= 9:
            roman += 'IX'
            number -= 9
        elif number >= 5:
            roman += 'V'
            number -= 5
        elif number >= 4:
            roman += 'IV'
            number -= 4
        else: 
            roman += 'I'
            number -= 1
    return roman

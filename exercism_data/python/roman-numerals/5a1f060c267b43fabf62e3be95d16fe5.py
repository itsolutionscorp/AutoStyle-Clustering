def numeral(arabic_num):
    number = arabic_num
    numeral = ''
    while number > 0:
        if number > 999:
            number -= 1000
            numeral += 'M'
        elif number > 899:
            number -= 900
            numeral += 'CM'
        elif number > 499:
            number -= 500
            numeral += 'D'
        elif number > 399:
            number -= 400
            numeral += 'CD'
        elif number > 99:
            number -= 100
            numeral += 'C'
        elif number > 89:
            number -= 90
            numeral += 'XC'
        elif number > 49:
            number -= 50
            numeral += 'L'
        elif number > 39:
            number -= 40
            numeral += 'XL'
        elif number > 9:
            number -= 10
            numeral += 'X'
        elif number > 8:
            number -= 9
            numeral += 'IX'
        elif number > 4:
            number -= 5
            numeral += 'V'
        elif number > 3:
            number -= 4
            numeral += 'IV'
        else:
            number -= 1
            numeral += 'I'
    return numeral

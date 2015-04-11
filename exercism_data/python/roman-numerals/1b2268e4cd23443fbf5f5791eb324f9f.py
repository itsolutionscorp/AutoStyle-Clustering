def numeral(number):
    numerals = {1: 'I', 4: 'IV', 5: 'V', 9:'IX', 10: 'X', 40: 'XL', 50: 'L',
                90:'XC', 100: 'C', 400:'CD', 500: 'D', 900:'CM', 1000: 'M' }

    arabic = str(number)
    exp = len(arabic) - 1
    numeral = ''
    for i, dig in enumerate(arabic):
        value = int(dig)*10**(exp-i)
        while value > 0:
            bigvalue = max([v for v in numerals if v<=value])
            value-=bigvalue
            numeral+=numerals[bigvalue]

    return numeral

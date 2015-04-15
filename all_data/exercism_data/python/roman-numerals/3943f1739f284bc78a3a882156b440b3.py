def numeral(arabic):
    lookup = [
        {1: 'I', 5: 'V'},
        {1: 'X', 5: 'L'},
        {1: 'C', 5: 'D'},
        {1: 'M'},
    ]

    rev_roman = []
    for place, digit in enumerate(str(arabic)[::-1]):

        digit = int(digit)

        ones = lookup[place][1]

        if place == 3:
            fives = ""
            tens = ""
        else:
            fives = lookup[place][5]
            tens = lookup[place + 1][1]

        if digit <= 3:
            roman = ones * digit
        elif digit == 4:
            roman = ones + fives
        elif digit <= 8:
            roman = fives + ones * (digit - 5)
        else:
            roman = ones + tens

        rev_roman.append(roman)

    return "".join(rev_roman[::-1])

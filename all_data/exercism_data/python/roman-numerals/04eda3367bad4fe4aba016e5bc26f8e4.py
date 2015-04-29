numeral_map = [
    (1000, 'M'),
    (500, 'D'),
    (100, 'C'),
    (50, 'L'),
    (10, 'X'),
    (5, 'V'),
    (1, 'I')
]

def numeral(number):
    output = ""
    for index, pair in enumerate(numeral_map):
        arabic, roman = pair
        next_quotient = False

        if index < len(numeral_map) - 1:
            next_arabic = numeral_map[index + 1][0]
            next_quotient = number // next_arabic

        if next_quotient not in [4, 9] or index % 2 == 0:
            output += roman * (number // arabic)
            number %= arabic
            continue

        next_roman = numeral_map[index + 1][1]
        previous_roman = numeral_map[index - 1][1]

        if next_quotient == 9:
            output += (next_roman + previous_roman)
            number %= next_arabic
        elif next_quotient == 4:
            output += (next_roman + roman)
            number %= next_arabic

    return output

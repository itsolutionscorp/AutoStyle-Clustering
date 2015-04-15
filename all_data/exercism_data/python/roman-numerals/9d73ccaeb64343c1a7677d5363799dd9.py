ARABIC = (1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
ROMAN = ('M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I')

KEY = dict(zip(ARABIC, ROMAN))


def numeral(arabic):
    roman = []
    for n in ARABIC:
        factor, arabic = divmod(arabic, n)
        roman.append(factor * KEY[n])
        if not arabic:
            break
    return ''.join(roman)

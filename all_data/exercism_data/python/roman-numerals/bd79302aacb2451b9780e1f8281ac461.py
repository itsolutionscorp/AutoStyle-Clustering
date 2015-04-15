roman_digits = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX']

def numeral(arabic):
    roman_number = ''
    for base in [1000, 100, 10, 1]:
        n, arabic = divmod(arabic, base)
        roman_number += rnum(n, base)

    return roman_number

def rnum(n, base):
    num = roman_digits[n]
    if base >= 1000:
        num.translate(str.maketrans('I', 'M'))
    elif base >= 100:
        num.translate(str.maketrans('IVX', 'XLC'))

    return num

ROMAN_SYMBOLS = {
        1: 'I',
        5: 'V',
        10: 'X',
        50: 'L',
        100: 'C',
        500: 'D',
        1000: 'M',
        }
RULES = [[],
         [1],
         [1, 1],
         [1, 1, 1],
         [1, 5],
         [5],
         [5, 1],
         [5, 1, 1],
         [5, 1, 1, 1],
         [1, 10]
        ]

def numeral(arabic, symbols = ROMAN_SYMBOLS):
    if arabic < 0 or arabic > 3000:
        raise ValueError
    if arabic == 0 or len(symbols) == 0:
        return ''
    else:
        last_digit = ''.join(symbols[x] for x in RULES[arabic % 10])
        reduced_symbols = {int(n / 10): s for n, s in symbols.items()}
        higher_digits = numeral(int(arabic / 10), reduced_symbols)
        return higher_digits + last_digit

table = [
    (1000, 'M'),
    (900, 'CM'),
    (500, 'D'),
    (400, 'CD'),
    (100, 'C'),
    (90, 'XC'),
    (50, 'L'),
    (40, 'XL'),
    (10, 'X'),
    (9, 'IX'),
    (5, 'V'),
    (4, 'IV'),
    (1, 'I'),
]

def numeral(arabic):
    rest = arabic
    result = []
    for n, chars in table:
        qtd, rest = divmod(rest, n)
        if qtd:
            result.append(qtd * chars)
    return ''.join(result)

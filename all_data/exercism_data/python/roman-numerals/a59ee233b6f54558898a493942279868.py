NUMERALS = {
    1: 'I',
    2: 'II',
    3: 'III',
    5: 'V',
    10: 'X',
    100: 'C',
    500: 'D',
    1000: 'M',
}

def numeral(arabic):
    n = arabic
    out = ""
    while n >= 1000:
        out += 'M'
        n -= 1000
    if n >= 900:
        out += 'CM'
        n -= 900
    if n >= 500:
        out += 'D'
        n -= 500
    if n >= 400:
        out += 'CD'
        n -= 400
    if n >= 100:
        num_cs = n / 100
        out += 'C' * num_cs
        n -= (num_cs*100)
    if n >= 90:
        out += 'XC'
        n -= 90
    if n >= 50:
        out += 'L'
        n -= 50
    if n >= 40:
        out += 'XL'
        n -= 40
    if n >= 10:
        num_xs = n / 10
        out += 'X' * num_xs
        n -= (num_xs*10)
    if n == 9:
        out += 'IX'
        return out
    if n >= 5:
        out += 'V'
        n -= 5
    if n == 4:
        out += 'IV'
        return out
    else:
        return out + 'I' * n

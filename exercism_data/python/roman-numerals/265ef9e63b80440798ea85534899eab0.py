def numeral(n):
    numerals = list(zip([1000, 900, 500, 400, 100,90, 50, 40, 10, 9, 5, 4, 1],
                        ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]))
    s = ''

    for a, r in numerals:
        while n >= a:
            n -= a
            s += r

    return s

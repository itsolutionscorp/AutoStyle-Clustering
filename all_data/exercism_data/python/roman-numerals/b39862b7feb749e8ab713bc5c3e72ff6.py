roman_numeral_map = (
        ('M',  1000),
        ('CM', 900),
        ('D',  500),
        ('CD', 400),
        ('C',  100),
        ('XC', 90),
        ('L',  50),
        ('XL', 40),
        ('X',  10),
        ('IX', 9),
        ('V',  5),
        ('IV', 4),
        ('I',  1))

def numeral(n):
    if not 0 < n < 3999:
        raise ValueError('n should be in range(1, 3999)')

    result = ''
    for numeral, integer in roman_numeral_map:
        while n >= integer:
                result += numeral
                n -= integer

    return result

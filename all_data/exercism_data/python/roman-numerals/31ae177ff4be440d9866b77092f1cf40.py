'''
    Table must be in the following order (highest to lowest)
'''
    
roman_arabic_table = (
    ('M',  1000),
    ('CM',  900),
    ('D',   500),
    ('CD',  400),
    ('C',   100),
    ('XC',   90),
    ('L',    50),
    ('XL',   40),
    ('X',    10),
    ('IX',    9),
    ('V',     5),
    ('IV',    4),
    ('I',     1)
)


def numeral(number):
    if not 1 <= number <= 3999:
        raise ValueError(
            "{} out of range for a Roman Numeral".format(number))
    result = ''
    for letter, value in roman_arabic_table:
        while number >= value:
            result += letter
            number -= value
    return result

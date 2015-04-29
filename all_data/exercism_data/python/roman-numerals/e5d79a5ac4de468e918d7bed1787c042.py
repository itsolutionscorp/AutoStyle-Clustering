import re

roman_numbers = (
    ( 'M', 1000 ),
    ( 'D',  500 ),
    ( 'C',  100 ),
    ( 'L',   50 ),
    ( 'X',   10 ),
    ( 'V',    5 ),
    ( 'I',    1 )
)

roman_shorthand = (
    ( 'DCCCC', 'CM' ),
    ( 'CCCC',  'CD' ),
    ( 'LXXXX', 'XC' ),
    ( 'XXXX',  'XL' ),
    ( 'VIIII', 'IX' ),
    ( 'IIII',  'IV' )
)

def numeral(arabic):

    if arabic > 3000:
        raise ValueError("Romans did not count that high!")

    roman = "" 

    for roman_number, value in roman_numbers:
        while arabic - value >= 0:
            roman += roman_number
            arabic -= value

    for full, shorthand in roman_shorthand:
        roman = roman.replace(full, shorthand, 1)

    return roman

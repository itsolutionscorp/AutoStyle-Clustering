roman_numbers = (
    ( 'M',  1000 ), ( 'CM',  900 ),
    ( 'D',   500 ), ( 'CD',  400 ),
    ( 'C',   100 ), ( 'XC',   90 ),
    ( 'L',    50 ), ( 'XL',   40 ),
    ( 'X',    10 ), ( 'IX',    9 ),
    ( 'V',     5 ), ( 'IV',    4 ),
    ( 'I',     1 )
)

def numeral(arabic):

    if arabic < 1:
        raise ValueError("Romans did not count below 1")
    if arabic > 3000:
        raise ValueError("Romans did not count beyond 3000")

    roman = ""

    for roman_number, value in roman_numbers:
        while arabic >= value:
            roman += roman_number
            arabic -= value

    return roman

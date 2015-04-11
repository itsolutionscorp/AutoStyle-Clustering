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

def numeral(number):

    romans = "" 
    for roman, decimal in roman_numbers:
        while number - decimal >= 0:
            romans += roman
            number -= decimal

    romans = re.sub('VIIII', 'IX', romans)
    romans = re.sub('IIII',  'IV', romans)
    romans = re.sub('XXXX',  'XL', romans)
    romans = re.sub('DCCCC', 'CM', romans)
    romans = re.sub('CCCC',  'CD', romans)
    romans = re.sub('LXL',   'XC', romans)

    return romans

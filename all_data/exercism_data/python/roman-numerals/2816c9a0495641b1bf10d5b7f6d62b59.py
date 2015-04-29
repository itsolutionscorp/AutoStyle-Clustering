def numeral(arabic):
    
    base_numerals = (
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
        (1, 'I')
        )
    
    roman_num = ''
    for number, letter in base_numerals:
        while arabic >= number:
            roman_num += letter
            arabic -= number
    return roman_num

def numeral(arabic):
    """Write a function to convert from normal numbers to Roman Numerals"""
    
    ROMANS = {
        1000: 'M',
        900: 'CM',
        500: 'D',
        400: 'CD',
        100: 'C',
        90: 'XC',
        50: 'L',
        40: 'XL',
        10: 'X',
        9: 'IX',
        5: 'V',
        4: 'IV',
        1: 'I'
    }
    
    roman = ''
    reverse_mapping = sorted(ROMANS.iteritems(), reverse = True)
    
    for current_arabic, current_roman in reverse_mapping:
        while arabic >= current_arabic:
            roman += current_roman
            arabic -= current_arabic
    return roman

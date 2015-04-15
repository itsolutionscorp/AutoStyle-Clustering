def numeral(arabic):
    
    return ''.join([replace_digit(digit,mag) for digit,mag in get_magnitudes(arabic)])

def replace_digit(num, magnitude):
    symbol_lookup = {
            0    : ('I','V','X'),
            1   : ('X','L','C'),
            2  : ('C','D','M'),
            3 : ('M','_','_')
    }
    unit, five, decimal = symbol_lookup[magnitude]
    digit_lookup = {
            '0' : '',
            '1' : unit,
            '2' : 2 * unit,
            '3' : 3 * unit,
            '4' : unit + five,
            '5' : five,
            '6' : five + unit,
            '7' : five + 2 * unit,
            '8' : five + 3 * unit,
            '9' : unit + decimal
    }
    return digit_lookup[num]

def get_magnitudes(number):
    return reversed([(digit, magnitude) for magnitude, digit in enumerate(reversed(str(number)))])

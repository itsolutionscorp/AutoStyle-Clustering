def numeral(arabic):
    if arabic < 1 or arabic > 3000:
        raise ValueError('Value outside of range')

    output = ''.join([translate_symbols(value, order) for order, value in get_magnitudes(arabic)])
    return output

def get_magnitudes(number):
    thousands, hundreds, tens, ones = [int(n) for n in str(number).zfill(4)]
    return zip(['thousands','hundreds','tens','ones'],[thousands,hundreds,tens,ones])

def translate_symbols(num, magnitude):
    symbol_lookup = {
            'ones'      : ('I','V','X'),
            'tens'      : ('X','L','C'),
            'hundreds'  : ('C','D','M'),
            'thousands' : ('M','_','_')
    }
    unit, five, decimal = symbol_lookup[magnitude]
    digit_lookup = {
            0 : '',
            1 : unit,
            2 : 2 * unit,
            3 : 3 * unit,
            4 : unit + five,
            5 : five,
            6 : five + unit,
            7 : five + 2 * unit,
            8 : five + 3 * unit,
            9 : unit + decimal
    }
    return digit_lookup[num]

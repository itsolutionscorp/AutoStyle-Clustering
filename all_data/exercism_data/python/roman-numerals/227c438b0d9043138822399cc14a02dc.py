ONES = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX']
TENS = ['', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC']
HUNDREDS = ['', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM']
THOUSANDS = ['', 'M', 'MM', 'MMM']


def numeral(arabic):
    # Find the digits at each order of magnitude
    ones = ONES[arabic % 10]
    tens = TENS[(arabic % 100)/10]
    hundreds = HUNDREDS[(arabic % 1000)/100]
    thousands = THOUSANDS[(arabic % 10000)/1000]

    return thousands + hundreds + tens + ones

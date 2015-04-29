# List of equivalences based on the digit's value
# I'm assuming that (per the instructions) numbers won't be greater than 3,000
NUMERALS = {'4':['IV','XL','CD'],'9':['IX','XC','CM'],'<4':['I','X','C','M'],'>5':['V','L','D']}

def numeral(arabic):
    if not isinstance(arabic, (int,long)):
        return ''
    result = []
    for index, digit in enumerate(str(arabic)[::-1]):
        result.insert(0,convert_any(digit,index))
    return ''.join(result)

def convert_any(digit,index):
    if digit == '0':
        return ''
    digit = int(digit)
    if digit < 5:
        return NUMERALS['4'][index] if digit == 4 else NUMERALS['<4'][index] * digit
    else:
        return NUMERALS['9'][index] if digit == 9 else NUMERALS['>5'][index] + NUMERALS['<4'][index] * (digit - 5)

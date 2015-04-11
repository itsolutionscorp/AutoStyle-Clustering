def _is_digit(digit):
    if digit in '01':
        return True
    raise ValueError('Not a valid input.')
    
def parse_binary(input):
    return sum([pow(2,i)*int(digit) for i, digit in enumerate(list(input)[::-1]) if _is_digit(digit)])

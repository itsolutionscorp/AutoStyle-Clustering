import re


def parse_octal(raw_string):
    if not re.match('^[0-7]+$', raw_string):
        raise ValueError('Not a valid octal number.')

    result = 0
    for index, digit in enumerate(reversed(raw_string)):
        result += int(digit) * 8**index
    return result

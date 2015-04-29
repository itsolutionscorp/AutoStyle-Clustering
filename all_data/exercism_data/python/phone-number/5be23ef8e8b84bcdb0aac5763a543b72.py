US_CODE = "1" # US Dialing Code
AREA_CODE_LEN = 3
PREFIX_LEN = 3
LINE_NUMBER_LEN = 4

PHONE_LEN = AREA_CODE_LEN + PREFIX_LEN + LINE_NUMBER_LEN

FULL_PHONE_LEN = PHONE_LEN + len(US_CODE)

INVALID_NUMBER = "0" * PHONE_LEN

def parse_phone(raw_phone_string):
    '''gets a valid phone number as a string of digits
        (or zeroes if invalid)'''
    raw_digits = filter(str.isdigit,raw_phone_string)

    if len(raw_digits) == PHONE_LEN:
        return raw_digits
    elif len(raw_digits) == FULL_PHONE_LEN \
            and raw_digits.startswith(US_CODE):
        return raw_digits[len(US_CODE):]
    else:
        return INVALID_NUMBER


class Phone(object):
    '''Represents a US Telephone number'''

    def __init__(self, raw_phone_string):
       self.number = parse_phone(raw_phone_string)

    def area_code(self):
        return self.number[:AREA_CODE_LEN]

    def prefix(self):
        return self.number[AREA_CODE_LEN:AREA_CODE_LEN+PREFIX_LEN]

    def line_number(self):
        return self.number[AREA_CODE_LEN+PREFIX_LEN:]

    def pretty(self):
        phone_format = "({area_code}) {prefix}-{line_number}"
        return phone_format.format(
            area_code = self.area_code(),
            prefix = self.prefix(),
            line_number = self.line_number())

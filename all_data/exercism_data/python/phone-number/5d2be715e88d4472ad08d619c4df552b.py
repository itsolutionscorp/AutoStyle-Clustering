def parse_phone(raw_phone_string):
    '''gets a valid phone number as a string of digits
        (or zeroes if invalid)'''
    raw_digits = filter(str.isdigit,raw_phone_string)

    if len(raw_digits) == 10:
        # phone with area code
        return raw_digits
    elif len(raw_digits) == 11 and raw_digits[0] == "1":
        # phone with leading country code
        return raw_digits[1:] # strip country code
    else:
        # invalid number
        return "0000000000"


class Phone(object):

    def __init__(self, raw_phone_string):
       self.number = parse_phone(raw_phone_string)

    def area_code(self):
        return self.number[:3]

    def prefix(self):
        return self.number[3:6]

    def line_number(self):
        return self.number[6:]

    def pretty(self):
        phone_format = "({area_code}) {prefix}-{line_number}"
        return phone_format.format(
            area_code = self.area_code(),
            prefix = self.prefix(),
            line_number = self.line_number())

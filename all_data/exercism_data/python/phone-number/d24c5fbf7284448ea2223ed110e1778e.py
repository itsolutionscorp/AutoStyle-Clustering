import re


class Phone(object):
    def __init__(self, input_number):
        self.number = self.sanitize(input_number)

    def sanitize(self, input_number):
        bad_number = "0"*10
        digits = re.sub(r'[^\d]', '', input_number)
        if len(digits) < 10 or len(digits) > 11:
            return bad_number
        elif len(digits) == 10:
            return digits
        else:
            if digits[0] == '1':
                return digits[1:]
            else:
                return bad_number

    def area_code(self):
        return self.number[0:3]

    def pretty(self):
        return ("({area_code}) {local}-{last4}"
                .format(area_code=self.area_code(),
                        local=self.number[3:6],
                        last4=self.number[6:]))

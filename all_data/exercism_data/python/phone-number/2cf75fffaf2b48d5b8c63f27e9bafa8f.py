import re


INVALID_NUMBER = '0' * 10


class Phone(object):
    def __init__(self, raw_string):
        self.number = self._clean(raw_string)

    def _clean(self, raw_string):
        digits = re.sub(r'[\D+]', '', raw_string)
        if len(digits) == 11 and digits[0] == '1':
            digits = digits[1:]
        if len(digits) != 10:
            digits = INVALID_NUMBER
        return digits

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return '(%s) %s-%s' % (
            self.area_code(),
            self.number[3:6],
            self.number[6:]
        )

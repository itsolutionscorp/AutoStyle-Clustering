import re


INVALID_NUMBER = '0' * 10


class Phone(object):
    def __init__(self, raw_string):
        number = re.sub(r'[^\d+]', '', raw_string)

        if len(number) < 10:
            self.number = INVALID_NUMBER
        elif len(number) == 10:
            self.number = number
        elif len(number) == 11:
            if number[0] == '1':
                self.number = number[1:]
            else:
                self.number = INVALID_NUMBER
        else:
            self.number = INVALID_NUMBER

    def __repr__(self):
        return self.number

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return '(%s) %s-%s' % (self.number[:3], self.number[3:6], self.number[6:])

import re


class Phone(object):
    def __init__(self, number):
        number = re.subn('[\s\(\-\)\.]', '', number)[0]
        LEN = len(number)
        if LEN == 10:
            self.number = number
        elif LEN == 11 and number.startswith('1'):
            self.number = number[1:]
        else:
            self.number = "0" * 10

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "({0}{1}{2}) {3}{4}{5}-{6}{7}{8}{9}".format(*self.number)

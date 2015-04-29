import string

BAD_NUMBER = "0000000000"
NOT_DIGITS = string.whitespace+string.punctuation+string.letters

class Phone(object):
    def __init__(self, number):
        num = number.translate(None, NOT_DIGITS)
        if len(num) == 10:
            self.number = num
        elif len(num) == 11 and num[0] == '1':
            self.number = num[1:]
        else:
            self.number = BAD_NUMBER

        self.area = self.number[:3]
        self.first = self.number[3:6]
        self.second = self.number[6:]

    def area_code(self):
        return self.area

    def pretty(self):
        return "({0}) {1}-{2}".format(self.area, self.first, self.second)

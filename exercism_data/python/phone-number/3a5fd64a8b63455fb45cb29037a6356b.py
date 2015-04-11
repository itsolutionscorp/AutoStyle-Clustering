__author__ = 'angelo'
import string as s

class Phone():

    def __init__(self, num):
        stripped = ''.join([n for n in num if n in s.digits])
        if len(stripped) == 10:
            self.number = stripped
        elif len(stripped) == 11 and stripped[0] == '1':
            self.number = stripped[1:]
        else:
            self.number = '0' * 10


    def area_code(self):
        return self.number[:3]

    def exchange(self):
        return self.number[3:6]

    def last_four(self):
        return self.number[-4:]

    def pretty(self):
        return "({0}) {1}-{2}".format(self.area_code(), self.exchange(), self.last_four())

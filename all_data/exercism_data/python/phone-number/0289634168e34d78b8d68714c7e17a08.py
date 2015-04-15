import re

class Phone(object):

    def __init__(self, num):
        self.number = re.sub(r'\D', '', num)
        length = len(self.number)
        if length == 11 and self.number[0] == '1':
            self.number = self.number[1:]
        elif length != 10:
            self.number = '0' * 10

    def area_code(self):
        return self.number[0:3]

    def pretty(self):
        return '({0}) {1}-{2}'.format(self.number[0:3],
                                      self.number[3:6],
                                      self.number[6:10])

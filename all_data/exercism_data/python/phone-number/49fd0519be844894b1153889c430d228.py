import re


class Phone(object):
    def __init__(self, n):
        n = re.sub(r'\D', '', n)  # remove any non-digits
        if len(n) == 10 or (len(n) == 11 and n[0] == '1'):
            self.number = n[-10:]
        else:
            self.number = "0"*10

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "({0}) {1}-{2}".format(self.number[:3],
                                      self.number[3:6],
                                      self.number[6:])

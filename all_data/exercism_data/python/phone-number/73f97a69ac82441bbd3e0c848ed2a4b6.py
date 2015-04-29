import re

class Phone(object):

    def __init__(self, number):
        self.number = self._normalize(number)
        
    def _normalize(self, number):
        number = re.sub("\D+", "", number)
        if len(number) == 11 and number.startswith("1"):
            number = number[1:]
        if len(number) != 10:
            number = "0000000000"
        return number

    def area_code(self):
        return self.number[0:3]

    def pretty(self):
        n = self.number
        return "(%s) %s-%s" % (n[0:3], n[3:6], n[6:10])

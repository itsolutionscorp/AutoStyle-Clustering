import re

class Phone:
    BLANK_NUMBER = '0000000000'

    def __init__(self, raw_number):
        self.number = self._limit_length(self._parse(raw_number))

    def area_code(self):
        return self.number[0:3]

    def prefix(self):
        return self.number[3:6]

    def suffix(self):
        return self.number[6:10]

    def pretty(self):
        return "(%s) %s-%s" % (self.area_code(), self.prefix(), self.suffix())



    def _parse(self, number):
        return re.sub('[^0-9]', '', number)

    def _limit_length(self, number):
        if len(number) is 10:
            return number
        elif len(number) is 11 and number[:1] is '1':
            return number[1:11]
        else:
            return self.BLANK_NUMBER

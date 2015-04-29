import re

class Phone:
    def __init__(self, number):
        self.number = self.process(number)

    def process(self, number):
        number = re.sub(re.compile("[^0-9]"), '', number)
        if len(number) == 11 and number[0] == "1":
            return number[1:]
        elif len(number) != 10:
            return "0000000000"
        else:
            return number

    def pretty(self):
        return "({0}) {1}-{2}".format(self.number[:3],
                                      self.number[3:6],
                                      self.number[6:])

    def area_code(self):
        return self.number[:3]

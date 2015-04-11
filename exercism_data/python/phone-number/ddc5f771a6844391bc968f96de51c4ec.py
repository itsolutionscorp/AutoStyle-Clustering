import re

class Phone(object):
    def __init__(self, number):
        cleaned = re.sub(r"\D+", "", number)
        if len(cleaned) == 11 and cleaned.startswith("1"):
            self.number = cleaned[1:]
        elif len(cleaned) == 10:
            self.number = cleaned
        else:
            self.number = "0000000000"

    def area_code(self):
        return self.number[0:3]

    def prefix(self):
        return self.number[3:6]

    def line_number(self):
        return self.number[6:10]

    def pretty(self):
        return "({0}) {1}-{2}".format(
                self.area_code(),
                self.prefix(),
                self.line_number())

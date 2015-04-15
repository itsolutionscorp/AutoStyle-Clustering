import re

class Phone:

    def __init__(self, input):
        self.number = self.clean(input)

    def clean(self, digits):
        digits = re.sub("[\D]", "", digits)
        if len(digits) == 11 and digits.startswith("1"):
            digits = digits[1:]
        if len(digits) != 10:
            digits = "0000000000"
        return digits

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "({0}) {1}-{2}".format(self.area_code(), self.number[3:6], self.number[6:])

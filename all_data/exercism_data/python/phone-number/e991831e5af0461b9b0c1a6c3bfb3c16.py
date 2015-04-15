import re

class Phone(object):
    """
        * If the phone number is less than 10 digits assume that it is bad number
        * If the phone number is 10 digits assume that it is good
        * If the phone number is 11 digits and the first number is 1, trim the 1 and use the first 10 digits
        * If the phone number is 11 digits and the first number is not 1, then it is a bad number
        * If the phone number is more than 11 digits assume that it is a bad number
    """
    def __init__(self, number):
        digits = re.sub(r"\D", "", number)

        if len(digits) == 10:
            self.number = digits
        elif len(digits) == 11 and digits[0] == "1":
            self.number = digits[1:]
        else:
            self.number = "0" * 10

    def area_code(self):
        return self.number[0:3]

    def pretty(self):
        return "({0}) {1}-{2}".format(self.area_code(), self.number[3:6], self.number[6:])

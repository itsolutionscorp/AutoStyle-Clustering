import re

class Phone:
    """ Phone takes a phone number and determines if it is bad,
    then provides area code and pretty printing """

    INVALID_NUMBER = "0000000000"

    def __init__(self, phoneNumber):
        self.number = re.sub(r'[^0-9]','', phoneNumber)
        if len(self.number) < 10 or len(self.number) > 11:
            self.number = Phone.INVALID_NUMBER
        if len(self.number) == 11:
            if self.number[0] != '1':
                self.number = Phone.INVALID_NUMBER
            else:
                self.number = self.number[1:]

    def area_code(self):
        return self.number[0:3]

    def pretty(self):
        return "(" + self.area_code() + ") " + self.number[3:6] + "-" + self.number[6:]

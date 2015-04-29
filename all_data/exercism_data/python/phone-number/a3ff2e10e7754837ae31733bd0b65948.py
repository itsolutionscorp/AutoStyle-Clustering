import re

class Phone(object):
    NULL_PHONE_NUMBER = "0000000000"

    def __init__(self, phone_number):
        cleaned_phone_number = re.sub(r'[^\d]', '', phone_number)
        if len(cleaned_phone_number) == 10:
            self.number = cleaned_phone_number
        elif len(cleaned_phone_number) == 11 and cleaned_phone_number[0] == '1':
            self.number = cleaned_phone_number[1:]
        else:
            self.number = self.NULL_PHONE_NUMBER

    def area_code(self):
        return self.number[0:3]

    def pretty(self):
        return "(%s) %s-%s" % (self.number[0:3], self.number[3:6], self.number[6:])

import string

class Phone:

    def __init__(self, number):
        number = ''.join(ch for ch in number if ch in string.digits)

        if self.is_valid_number(number):
            self.number = number[-10:]
        else:
            self.number = "0" * 10

    def is_valid_number(self, number):
        if len(number) == 11:
            if number[0] != '1':
                return False

        elif len(number) != 10:
            return False

        return True

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "(%s) %s-%s" % (self.number[:3], self.number[3:6], self.number[6:])

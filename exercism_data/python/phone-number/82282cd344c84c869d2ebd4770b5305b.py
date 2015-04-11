extra = ['(', ')', '-', '.', ' ']

class Phone(object):

    def __init__(self, input_number):
        self.number = ''.join(c for c in input_number if c not in extra)
        self.length = len(self.number)

        if self.length == 11 and self.number[0] == '1':
            self.number = self.number[1:]
        elif self.length != 10:
            self.number = '0'*10

    def pretty(self):
        return "({0}) {1}-{2}".format(self.number[:3], self.number[3:6], self.number[6:])

    def area_code(self):
        return self.number[0:3]

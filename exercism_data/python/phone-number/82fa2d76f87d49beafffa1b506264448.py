BAD_NUMBER = "0000000000"

class Phone(object):
    def __init__(self, number):
        format_number = ""
        for char in number:
            if char.isdigit():
                format_number = format_number + char
        if len(format_number) < 10 or len(format_number) > 11:
            self.number = BAD_NUMBER
        elif len(format_number) == 10:
            self.number = format_number
        else:
            if format_number[0] == '1':
                self.number = format_number[1:]
            else:
                self.number = BAD_NUMBER

    def area_code(self):
        return self.number[0:3]

    def pretty(self):
        return "({0}) {1}-{2}".format(self.area_code(),self.number[3:6],self.number[6:])

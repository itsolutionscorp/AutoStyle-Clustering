class  Phone(object):
    def __init__(self, number):
        self.number = self.clean(number)

    def clean(self, number):
        cleannumber = ''
        for c in number:
            if c.isdigit():
                cleannumber += c

        if len(cleannumber) == 10:
            return cleannumber
        if len(cleannumber) == 11 and cleannumber[0] == '1':
            return cleannumber[1:]
        return "0000000000"

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "({0}) {1}-{2}".format(self.area_code(),
                                      self.number[3:6],
                                      self.number[6:])


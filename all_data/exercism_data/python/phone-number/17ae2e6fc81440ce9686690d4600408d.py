from string import digits


class Phone:
    def __init__(self, num):
        self.orignum = num
        self.number = ''
        self.area = ''
        self.exchange = ''
        self.station = ''
        self.parsenum()

    def parsenum(self):
        self.number = ''.join(c for c in self.orignum if c in digits)
        l = len(self.number)
        if (not 10 <= l <= 11) or (l == 11 and self.number[0] != '1'):
            self.number = '0000000000'
        elif l == 11:
            self.number = self.number[1:]
        self.area = self.number[0:3]
        self.exchange = self.number[3:6]
        self.station = self.number[6:]

    def pretty(self):
        return '(' + self.area + ') ' + self.exchange + '-' + self.station

    def area_code(self):
        return self.area

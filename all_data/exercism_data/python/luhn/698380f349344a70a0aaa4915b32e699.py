__author__ = 'flaviomiranda'


class Luhn(object):
    value = 0
    ends = []
    isvalid = False

    def __init__(self, num=0):
        self.isvalid, self.value, self.ends = False, num, []
        self.convert()
        self.isvalid = sum(self.ends) % 10 == 0

    def convert(self):
        tmpnum, sequence = self.value, 0
        while tmpnum > 0:
            lstdgt = tmpnum % 10  # last digit
            if sequence % 2 == 1:
                self.ends.insert(0, 2 * lstdgt if lstdgt <= 4 else 2 * lstdgt - 9)
            else:
                self.ends.insert(0, lstdgt)
            tmpnum = int((tmpnum - lstdgt) / 10)  # take the last digit out
            sequence += 1
        return

    def addends(self):
        return self.ends

    def checksum(self):
        return sum(self.ends) % 10

    def is_valid(self):
        return self.isvalid

    @staticmethod
    def create(num):
        l = Luhn(num * 10)
        chksum = l.checksum()
        return num * 10 + (10 - chksum if chksum != 0 else chksum)

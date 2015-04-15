class Luhn(object):
    def __init__(self, number=0):
        self.number = number

    def addends(self):
        newnumber = ''
        for i, dig in enumerate(reversed(str(self.number))):
            if i%2 == 0:
                newnumber+=dig
            else:
                newnumber+=str(2*int(dig)) if int(dig) < 5 else str(2*int(dig)-9)
        return [int(c) for c in newnumber]

    def checksum(self):
        return sum(self.addends())%10

    def is_valid(self):
        return self.checksum() == 0

    @classmethod
    def create(cls, number):
        altChecksum = Luhn(10*number).checksum()
        return 10*number+(10-altChecksum)%10

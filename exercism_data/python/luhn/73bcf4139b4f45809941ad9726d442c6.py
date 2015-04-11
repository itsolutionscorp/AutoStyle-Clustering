class Luhn(object):
    def __init__(self, ncheck):
        self.ncheck = ncheck

    def addends(self):
        nStrRev = str(self.ncheck)[::-1]
        nListMod = [_modify_digit(int(n)) if idx%2 else int(n) for idx,n in enumerate(nStrRev)]
        nListMod.reverse()
        return nListMod

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return not self.checksum()

    @staticmethod
    def create(n):
        return n*10 + -Lund(n*10).checksum()%10


def _modify_digit(n):
    '''Returns the Luhn-modified digit'''
    return n*2 - (9 if n > 4 else 0)

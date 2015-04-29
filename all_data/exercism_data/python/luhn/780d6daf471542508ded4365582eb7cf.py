class Luhn(object):
    def __init__(self, number):
        self.digits = list(map(int,list(str(number))))
    
    def addends(self=None, digits=None):
        if digits is None:
            digits = self.digits
        result = list()
        for idx, digit in enumerate(reversed(digits)):
            if idx%2:
                if digit * 2 >= 10:
                    result.append(digit * 2 - 9)
                else:
                    result.append(digit * 2)
                # I wasted entirely too much time assuming that
                # num-9 is num > 10 was the same as num % 9.
            else:
                result.append(digit)
        return result[::-1]
    
    def checksum(self, digits=None):
        if digits is None:
            digits = self.digits
        return sum(self.addends(digits=digits)) % 10

    @staticmethod
    def create(digitstr):
        digits = list(map(int, list(str(digitstr)+"0")))
        checkdigit = (10 - Luhn.checksum(Luhn, digits=digits)) % 10
        return digitstr * 10 + checkdigit

    def is_valid(self):
        return self.checksum() == 0

from __future__ import division

class Luhn(long):
    def digits(self):
        return [int(a) for a in str(self)]

    def addends(self):
        addends = self.digits()
        addends[-2::-2] = [(2 * d - 1) % 9 + 1 for d in addends[-2::-2]]
        return addends

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    def fixed_checksum(self):
        number = self - self % 10
        number += (10 - self.checksum()) % 10
        return Luhn(number)

    @staticmethod
    def create(number):
        return Luhn(number * 10).fixed_checksum()

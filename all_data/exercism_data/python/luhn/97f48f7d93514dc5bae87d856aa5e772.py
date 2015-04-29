class Luhn:
    def __init__(self, number):
        self.number = number
        self.ends = [int(s) for s in str(number)]
        self.ends[-2::-2] = [(n * 2) - 9 if n > 4 else n * 2 for n in self.ends[-2::-2]]

    def addends(self):
        return self.ends

    def checksum(self):
        return sum(self.ends) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number):
        return number * 10 + (10 - Luhn(number * 10).checksum()) % 10

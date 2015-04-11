class Luhn:
    def __init__(self, number):
        self.number = number
        self.ends = [int(s) for s in str(number)]
        self.ends[-2::-2] = [(n * 2) % 9 for n in self.ends[-2::-2]]

    def addends(self):
        return self.ends

    def checksum(self):
        return sum(self.ends) % 10

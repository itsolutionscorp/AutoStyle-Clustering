class Luhn:

    def __init__(self, input=0):
        self.input = input

    def addends(self):
        list_of_ints = list(map(int, str(self.input)))
        for i, e in reversed(list(enumerate(list_of_ints))):
            if (len(list_of_ints) - i - 1) % 2 != 0:
                if 2 * e > 10:
                    list_of_ints[i] = 2 * e - 9
                else:
                    list_of_ints[i] = 2 * e
        return list_of_ints

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @classmethod
    def create(cls, n):
        lu = cls(n * 10)
        c = lu.checksum()
        if c != 0:
            lu.input += 10 - c
        return lu.input

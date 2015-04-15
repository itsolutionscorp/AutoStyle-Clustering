class Luhn():

    def __init__(self, num):
        self.raw_num = num
        self.digits = [int(char) for char in str(num)]

    def checksum(self):
        return sum(self.addends()) % 10

    def addends(self):
        addends = []
        for x in self.addend_generator():
            addends.insert(0, x)
        return addends

    def is_valid(self):
        return not self.checksum()

    def addend_generator(self):
        for i, x in enumerate(reversed(self.digits)):
            if i % 2:
                value = x * 2
                digit = value if value < 10 else value - 9
            else:
                digit = x
            yield digit

    @classmethod
    def create(cls, number):
        luhn = cls(number * 10)
        result = luhn.checksum()
        return int(str(number) + str(10 - result)[-1])

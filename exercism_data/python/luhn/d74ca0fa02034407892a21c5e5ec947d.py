class Luhn:
    def __init__(self, number=0):
        self.new_digits, self.checksum_digit = self.compute(number)

    @staticmethod
    def compute(val):
        a =  [int(x) for x in reversed(list(str(val)))]
        temp =  [(x*2)%9 if index%2==1 and x != 9 else x for index, x in enumerate(a)]
        b =  [x for x in reversed(temp)]

        return b,sum(b)

    def addends(self):
        return self.new_digits

    def checksum(self):
        return self.checksum_digit % 10

    def is_valid(self):
        return self.checksum_digit % 10 == 0

    @staticmethod
    def create(value):
        result = Luhn.compute(int(str(value) +'0'))
        if result[1] % 10 == 0:
            checksum = 0
        else:
            checksum = 10 - result[1] % 10
        return value*10 + checksum

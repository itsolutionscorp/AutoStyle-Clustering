class Luhn:

    def __init__(self, num):
        self.num = num

    def _luhnify(self, num):
        return (num * 2 - 9 if num > 4 else num * 2)

    def is_valid(self):
        return self.checksum() == 0

    def checksum(self):
        return sum(self.addends()) % 10

    def addends(self):
        digits_list = get_digits_list(self.num)
        return [(self._luhnify(n) if i % 2 == 0 else n) for i, n in
                enumerate(digits_list, start=len(digits_list) % 2)]  

    def create(num):
        return num * 10 + ((10 - Luhn(num * 10).checksum()) % 10)


def get_digits_list(num):
    return [int(x) for x in str(num)]

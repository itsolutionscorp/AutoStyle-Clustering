class Luhn():
    def __init__(self, numbers):
        self._numbers = numbers

    def addends(self):
        new_numbers = []
        for i, n in enumerate([int(num) for num in str(self._numbers)][::-1]):
            if i % 2:
                if n * 2 > 9:
                    new_numbers.insert(0, n * 2 - 9)
                else:
                    new_numbers.insert(0, n * 2)
            else:
                new_numbers.insert(0, n)
        return new_numbers

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return not bool(self.checksum())

    @staticmethod
    def create(incorrect_number):
        for n in range(10):
            test_number = int(str(incorrect_number) + str(n))
            if not Luhn(test_number).checksum():
                return test_number

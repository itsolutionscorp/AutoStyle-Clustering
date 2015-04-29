class Luhn(object):
    def __init__(self, number):
        self._number = number

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    def addends(self):
        addends = []
        digits = [int(digit) for digit in str(self._number)]
        for index, digit in enumerate(reversed(digits)):
            if index % 2:
                digit *= 2
                if digit >= 10:
                    digit -= 9
            addends.append(digit)
        return addends[::-1]

    @staticmethod
    def create(number):
        test_number = number * 10
        instance = Luhn(test_number)
        difference = 10 - instance.checksum()
        return test_number + (difference if difference < 10 else 0)

class Luhn:
    def __init__(self, number):
        self.number = number
        self.digits = [int(char) for char in str(number)]
        self.computed_checksum = -1

    def addends(self):
        i = -2
        end = -len(self.digits)
        while i >= end:
            self.digits[i] = self.double(self.digits[i])
            i -= 2
        return self.digits

    def checksum(self):
        self.addends()
        return sum(self.digits) % 10

    def is_valid(self):
        if self.computed_checksum == -1:
            self.computed_checksum = self.checksum()
        return self.computed_checksum == 0

    def create(number):
        luhn = Luhn(number)
        if luhn.is_valid():
            return number
        for i in range(10):
            new_number = number * 10 + i
            luhn = Luhn(new_number)
            if luhn.is_valid():
                return new_number

    def double(self, digit):
        new_digit = digit * 2
        if new_digit > 9:
            new_digit -= 9
        return new_digit

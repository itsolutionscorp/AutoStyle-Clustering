class Luhn:
    def __init__(self, number):
        self.number = number

    def addends(self):
        digits = []
        num = self.number
        is_even = False

        while num > 0:
            (num, digit) = divmod(num, 10)
            if is_even:
                digit *= 2
                if digit > 10:
                    digit -= 9
            digits.append(digit)
            is_even = not is_even

        digits.reverse()
        return digits

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(num):
        new_num = num * 10
        check = Luhn(new_num).checksum()
        if check != 0:
            new_num += 10 - check
        return new_num

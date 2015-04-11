__author__ = 'Eric'


class Luhn:

    def __init__(self, id_number):
        self.id_number = id_number

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return Luhn.checksum(self) == 0

    def addends(self):

        def digits_of(n):
            return [int(d) for d in str(n)]

        digits = digits_of(self.id_number)
        for i in range(len(digits)-2, -1, -2):
            digits[i] = sum(digits_of(digits[i]*2))
        return digits

    @staticmethod
    def create(id_number):
        id_number *= 10
        check_digit = Luhn(id_number).checksum()
        return id_number \
            if check_digit == 0 \
            else int(id_number + 10 - check_digit)

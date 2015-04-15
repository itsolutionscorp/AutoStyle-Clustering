class Luhn:
    def __init__(self, number):
        self.number = number

    def addends(self):
        digits = [ int(d) for d in str(self.number) ]
        for pos in range(len(digits) - 2, -1, -2):
            digits[pos] = Luhn._mangle(digits[pos])
        return digits

    @staticmethod 
    def _mangle(digit):
        digit = digit * 2
        return digit - 9 if digit > 10 else digit

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number):
        number_with_zero_checksum = number * 10
        checksum = Luhn(number_with_zero_checksum).checksum()
        checksum_required_for_valid_luhn = (10 - checksum) % 10
        return number_with_zero_checksum + checksum_required_for_valid_luhn

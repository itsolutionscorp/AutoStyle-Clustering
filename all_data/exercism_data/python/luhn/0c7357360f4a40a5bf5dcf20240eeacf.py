class Luhn(long):
    def addends(self):
        digits = [int(d) for d in str(self)]
        digits[-2::-2] = [Luhn._transform(d) for d in digits[-2::-2]]
        return digits

    @staticmethod 
    def _transform(digit):
        digit = digit * 2
        return digit - 9 if digit > 10 else digit

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number):
        return Luhn(number * 10).with_fixed_checksum()

    def with_fixed_checksum(self):
        number_part = self - (self % 10)
        check_digit = Luhn(number_part).checksum() * 9 % 10
        return Luhn(number_part + check_digit)

class Octal(object):

    def __init__(self,num):
        self._check_if_valid(num)
        self.num = num

    def to_decimal(self):
        decimal = 0
        for i, digit in enumerate(self.num[len(self.num)::-1]):
            decimal = decimal + int(digit) * 8**i
            
        return decimal

    def _check_if_valid(self, num):
        if not num.isdigit() or num.find('8') >= 0 or num.find('9') >= 0:
            raise ValueError("Invalid octal digit: %d" %(num))

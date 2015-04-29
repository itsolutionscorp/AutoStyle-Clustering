from itertools import izip_longest, chain

def _digits(number):
    return [int(n) for n in str(number)]

def _filter_none(numbers):
    return [x for x in numbers if x is not None]

class Luhn(object):
    def __init__(self, number):
        self.digits = _digits(number)

    def addends(self):
        odd_digits = self.digits[-1::-2]
        even_digits = [sum(_digits(n * 2)) for n in self.digits[-2::-2]]
        addends = list(chain(*izip_longest(odd_digits, even_digits)))
        return _filter_none(reversed(addends))

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @classmethod
    def create(cls, number):
        check_digit = (10 - cls(number * 10).checksum()) % 10
        return number * 10 + check_digit

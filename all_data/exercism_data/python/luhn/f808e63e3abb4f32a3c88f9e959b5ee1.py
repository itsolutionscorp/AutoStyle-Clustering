class Luhn(object):
    def __init__(self, number):
        self.number = number

    @classmethod
    def create(cls, number):
        proposed = number * 10
        luhn = cls(proposed)
        if luhn.is_valid():
            return proposed

        return proposed + (10 - luhn.checksum())

    def addends(self):
        return _process_formula(self.number)

    def is_valid(self):
        return self.checksum() == 0

    def checksum(self):
        return sum(self.addends()) % 10

def _process_formula(number):
    processed_digits = _transform_digits(
        _reversed_digits(number)
    )
    return list(reversed(processed_digits))

def _reversed_digits(number):
    digits = []
    while number > 0:
        digits.append(number % 10)
        number /= 10
    return digits

def _transform_digits(digits):
    def transform(digit, index):
        if index % 2 == 0:
            return digit

        doubled = digit * 2
        if doubled <= 9: return doubled
        return doubled - 9

    return [
        transform(digit, index)
        for index, digit in enumerate(digits)
    ]

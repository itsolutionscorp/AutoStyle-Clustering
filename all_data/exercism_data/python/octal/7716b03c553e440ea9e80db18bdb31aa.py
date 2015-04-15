class Octal:
    def __init__(self, octal):
        self.octal = octal
        self._check_validity(octal)

    def to_decimal(self):
        backwards = reversed(list(self.octal))
        total = 0
        for i, digit in enumerate(backwards):
            total += (8**i * int(digit))
        return total

    def _check_validity(self, octal):
        for d in list(octal):
            if str.isalpha(d) or int(d) > 7:
                raise ValueError("Invalid octal digit: " + d)

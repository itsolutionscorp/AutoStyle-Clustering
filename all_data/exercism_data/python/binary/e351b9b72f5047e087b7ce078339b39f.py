import re

def Binary(input):
    return BinaryToDecimal(input).convert()


class BinaryToDecimal(object):
    
    _VALID_INPUT_PATTERN = "^[01]+$"

    def __init__(self, input):
        self.input = input
        self.reversed_enumerable_input = enumerate(reversed(list(self.input)))

    def convert(self):
        if self._invalid_input():
            return 0
        return sum(
            self._calculate(number, index)
            for index, number in self.reversed_enumerable_input
        )

    def _invalid_input(self):
        return re.findall(self._VALID_INPUT_PATTERN, self.input) == []

    def _calculate(self, binary, index):
        return int(binary) * (2**index)

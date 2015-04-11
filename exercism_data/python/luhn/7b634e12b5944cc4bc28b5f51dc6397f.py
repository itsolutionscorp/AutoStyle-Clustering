__author__ = 'agupt15'


class Luhn:
    def __init__(self, num):
        # Strip all white spaces
        self.snum = str(num).strip()
        self._ends = None
        self.addends()

    def addends(self):
        if self._ends is not None:
            return self._ends
        self._ends = []
        # Reverse Iterate
        for i, x in enumerate(self.snum[::-1]):
            digit = int(x)
            # Every second digit
            if i % 2 == 1:
                if 2 * digit > 9:
                    self._ends.insert(0, 2 * digit - 9)
                else:
                    self._ends.insert(0, 2 * digit)
            else:
                self._ends.insert(0, digit)
        return self._ends

    def checksum(self):
        total = sum(self._ends)
        return total % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(x):
        for idx in range(10):
            new_number = x * 10 + idx
            if Luhn(new_number).is_valid():
                return new_number

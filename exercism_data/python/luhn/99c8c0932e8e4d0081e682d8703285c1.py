# exercism "Luhn"

class Luhn():
    def __init__(self, number):
        """Create a Luhn checkup from a number"""
        self.number = str(number)

    def addends(self):
        mapping = {str(k):str(2*k) for k in range(10)}
        output = [int(mapping[k] if idx%2 else k) for idx,k in enumerate(self.number[::-1])]
        output = [ k if k<10 else k-9 for k in output ]
        return output[::-1]

    def checksum(self):
        return sum(self.addends())%10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(n):
        for candidate in range(10):
            if Luhn(int(str(n)+str(candidate))).checksum() == 0:
                extra = str(candidate)
                break
        return int(str(n) + extra)

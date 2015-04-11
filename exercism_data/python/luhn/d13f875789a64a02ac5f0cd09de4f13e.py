class Luhn:
    def __init__(self, number):
        self.number = number

    @staticmethod
    def luhn(q):
        luhn = []
        index = 0
        while q:
            q, r = divmod(q, 10)
            if index % 2 == 1:
                r *= 2
                if r > 9:
                    r -= 9
            index += 1
            luhn.append(r)
        return reversed(luhn)

    @staticmethod
    def create(number):
        number = number * 10
        remainder = sum(Luhn.luhn(number)) % 10
        if remainder == 0:
            return number
        else:
            return number + (10 - remainder)

    def addends(self):
        return Luhn.luhn(self.number)

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

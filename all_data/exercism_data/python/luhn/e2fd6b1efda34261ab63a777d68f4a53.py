class Luhn:
    # This method is weird, it really should be a function as to use it
    # we aren't instantiating a class really.
    def create(new_num):
        temp_luhn = Luhn(new_num*10)
        if temp_luhn.is_valid():
            return new_num * 10
        else:
            check = sum(temp_luhn.addends())
            return new_num*10 + (10 - check % 10)

    def __init__(self, num):
        self.num = num

    def is_valid(self):
        return self.checksum() == 0

    def addends(self):
        res = []
        for i, n in enumerate(str(self.num)[::-1]):
            n = int(n)
            if i % 2 == 0:
                res.append(n)
            else:
                n *= 2
                if n >= 10:
                    n -= 9
                res.append(n)
        return res[::-1]

    def checksum(self):
        return sum(self.addends()) % 10

if __name__ == '__main__':
    print(Luhn(12121).addends())

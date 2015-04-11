class Luhn:

    def __init__(self, n=0):
        self.n = [int(i) for i in str(n).replace(' ', '')]
        adjust = 0 if bool(len(self.n) % 2) else 1

        for i, v in enumerate(self.n):
            if bool((i + adjust) % 2):
                v *= 2
                if v > 9:
                    v -= 9
                self.n[i] = v

    def addends(self):
        return self.n

    def checksum(self):
        self.n.reverse()
        ret = sum(self.n[0::2]) + sum(self.n[1::2]) % 10
        return [int(i) for i in str(ret)][-1]

    def is_valid(self):
        return bool(not self.checksum())

    @classmethod
    def create(cls, p):
        check = cls(p * 10).checksum()
        check = check if bool(not check) else 10 - check
        ret = [int(i) for i in str(p) + str(check)]
        return int(''.join(map(str, ret)))

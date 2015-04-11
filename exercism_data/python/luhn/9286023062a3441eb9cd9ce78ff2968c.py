class Luhn():
    def __init__(self, num = 0):
        self.start_num = [int(i) for i in str(num)]

    def addends(self):
        n = self.start_num
        out = []

        switch = not len(n)%2

        for i in n:
            if switch:
                a = 2*i
                if a>9:
                    a-=9
                out.append(a)
            else:
                out.append(i)

            switch = not switch

        return out

    @staticmethod
    def create(n):
        for i in range(10):
            test = Luhn(n*10 + i).is_valid()

            if test:
                return n*10 + i

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return not self.checksum() % 10

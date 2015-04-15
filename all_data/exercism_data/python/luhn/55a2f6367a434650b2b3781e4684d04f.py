class Luhn:

    def __init__(self, arg):
        self.num = arg

    def addends(self):
        i = 1
        ret = []
        flg = True
        while True:
            tmp = self.num / i
            if tmp == 0:
                break
            if flg:
                ret.insert(0, tmp % 10)
                flg = False
            else:
                if (tmp % 10) * 2 >= 10:
                    ret.insert(0, (tmp % 10) * 2 - 9)
                else:
                    ret.insert(0, (tmp % 10) * 2)
                flg = True
            i = i * 10
        return ret

    def checksum(self):
        ret = 0
        for x in self.addends():
            ret += x
        return ret % 10

    def is_valid(self):
        if self.checksum() % 10 == 0:
            return True
        return False

    @staticmethod
    def create(arg):
        ret = 0
        for i in range(10):
            obj = Luhn(arg * 10 + i)
            if obj.is_valid():
                ret = arg * 10 + i
                break
        return ret

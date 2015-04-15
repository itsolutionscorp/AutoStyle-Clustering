class Luhn(object):
    def __init__(self, number):
        self.number = [int(n) for n in list(str(number))]
        

    def addends(self):
        for n in range(len(self.number)):
            if (len(self.number) % 2 == 0 and n == 0) or (len(self.number) % 2 == 0 and n % 2 == 0) or (len(self.number) % 2 != 0 and n % 2 != 0):
                self.number[n] = self.number[n] * 2
            if self.number[n] > 9: self.number[n] -= 9
        return self.number

    def is_valid(self):
        if sum(self.addends()) % 10 == 0:
            return True
        else:
            return False

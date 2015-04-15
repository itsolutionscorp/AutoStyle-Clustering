class SumOfMultiples:
    def __init__(self, a = 3, b = 5, c = None, d = None):
        self.a = a
        self.b = b
        self.c = c
        self.d = d

    def to(self, limit):
        num_list = [n for n in range(1,limit)]
        multiples_list = []
        for num in num_list:
            if num % self.a == 0 or num % self.b == 0:
                multiples_list.append(num)
            elif self.c != None:
                if num % self.c == 0:
                    multiples_list.append(num)
            elif self.d != None:
                if num % self.d == 0:
                    multiples_list.append(num)
        return sum(multiples_list)

class SumOfMultiples:
    def __init__(self, *args):
        self.args = args

    def to(self, num):
        self.num = num
        L = []
        x = list(range(1, self.num))
        if self.args:
            L = [i for n in self.args for i in x if i % n == 0]
            # for n in self.args:
            #     for i in x:
            #         if i % n == 0:
            #             L.append(i)
            return sum(set(L))
        else:
            return sum([i for i in x if i % 3 == 0 or i % 5 == 0])

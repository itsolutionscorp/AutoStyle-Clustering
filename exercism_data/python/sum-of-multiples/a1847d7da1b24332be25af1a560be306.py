class SumOfMultiples:
    def __init__(self, *args):
        if args:
            self.multiples = [int(x) for x in args]
        else:
            self.multiples = [3, 5]

    def to(self, n):
        l = []
        for i in range(1, n):
            for n in self.multiples:
                if i % n == 0 or i == n:
                    l.append(i)

        l = list(set(l))
        return sum(l)

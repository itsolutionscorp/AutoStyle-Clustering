from sets import Set

class SumOfMultiples:
    def __init__(self, *args):
        if args:
            self.numbers = args
        else:
            self.numbers = (3, 5)

    def to(self, limit):
        multiples = Set()
        for n in self.numbers:
            for i in range(n, limit, n):
                multiples.add(i)
        return sum(multiples)

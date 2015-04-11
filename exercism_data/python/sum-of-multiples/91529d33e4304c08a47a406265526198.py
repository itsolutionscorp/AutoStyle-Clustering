class SumOfMultiples(object):
    def __init__(self, *args):
        if args:
            self.multiples = list(args)
        else:
            self.multiples = [3, 5]

    def to(self, limit):
        result = 0
        for x in range(0, limit):
            for m in self.multiples:
                if (x % m) == 0:
                    result += x
                    break
        return result

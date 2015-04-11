class SumOfMultiples:
    def __init__(self, *args):
        """ Given a number, finds the sum of all the multiples
            up to but not including that number.
            Default multiples are 3 and 5.
        """

        if args:
            self.multiples = list(args)
        else:
            self.multiples = [3, 5]

    def to(self, limit):
        return sum([i for i in range(limit)
                    if any(i % n == 0 for n in self.multiples)])

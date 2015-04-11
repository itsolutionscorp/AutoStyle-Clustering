class SumOfMultiples(object):
    def __init__(self, *multiples):
        self.multiples = multiples or (3,5)

    def to(self, limit):
        seen = set()
        for multiple in self.multiples:
            for num in range(multiple, limit, multiple):
            # enumerates all possible factors of multiple
                seen.add(num)
        return sum(seen)

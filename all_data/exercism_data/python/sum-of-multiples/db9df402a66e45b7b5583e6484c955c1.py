class SumOfMultiples(object):
    def __init__(self, *multiples):
        if not multiples:
            multiples = (3,5)
        self.multiples = multiples

    def to(self, limit):
        seen = set()
        for multiple in self.multiples:
            for num in range(multiple, limit, multiple):
                seen.add(num)
        return sum(seen)

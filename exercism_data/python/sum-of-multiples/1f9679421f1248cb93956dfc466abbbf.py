class SumOfMultiples():

    def __init__(self, *factors):
        if not factors:
            self.factors = [3, 5]
        else:
            self.factors = factors

    def to(self, limit):
        nums = range(2, limit)
        return sum(set(a for a in nums for b in self.factors if a % b == 0))

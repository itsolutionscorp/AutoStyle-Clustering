class SumOfMultiples:
    def __init__(self, *nums):
        self.n = nums or (3, 5)

    def to(self, n):
        return sum(filter(
            lambda x: any(not x % n for n in self.n),
            range(1, n)))

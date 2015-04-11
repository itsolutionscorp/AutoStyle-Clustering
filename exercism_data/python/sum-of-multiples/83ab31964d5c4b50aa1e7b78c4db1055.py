class SumOfMultiples(object):

    def __init__(self, *nums):
        self.numbers = nums or [3, 5]

    def to(self, limit):
        return sum([i for i in range(0, limit) if any([i % j == 0 for j in self.numbers])])

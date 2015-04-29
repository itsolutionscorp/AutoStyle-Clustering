class SumOfMultiples:
    def __init__(self, *nums):
        self.nums = nums or (3, 5)
    def to(self, limit):
        return(sum(filter(self._filter_function, range(0,limit))))
    def _filter_function(self, x):
        return(not all(map(lambda n: x % n, self.nums)))

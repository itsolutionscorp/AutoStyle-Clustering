class SumOfMultiples:
    def __init__(self, *nums):
        if len(nums) < 1:
            self.nums = [3, 5]
        else:
            self.nums = list(nums)
    def to(self, limit):
        return(sum(filter(self._filter_function, range(0,limit))))
    def _filter_function(self, x):
        return(not all(map(lambda n: x % n, self.nums)))

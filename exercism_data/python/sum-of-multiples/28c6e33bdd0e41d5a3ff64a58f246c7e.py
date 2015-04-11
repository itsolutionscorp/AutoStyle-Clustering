class SumOfMultiples:
    def __init__(self, *nums):
        self.nums = tuple(nums) or (3, 5)
    
    def to(self, last):
        return sum(i for i in xrange(1, last) if any(not i % j for j in self.nums))

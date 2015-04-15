class SumOfMultiples():
    def __init__(self, *nums):
        if (len(nums) == 0):
            nums = [3, 5]
        self.nums = nums 

    def to(self, n):
        res = set()
        for i in self.nums:
            res.update(range(0, n, i))
        return sum(res)

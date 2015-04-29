from operator import mul

class Series(object):
    def __init__(self, ns):
        self.nums = [int(i) for i in ns]

    def slices(self, n):
        nums = self.nums
        if len(nums) < n:
            raise ValueError("Invalid slice length for this series: {}".format(n))
        return [nums[i:i+n] for i in xrange(len(nums)-n+1)]

    def largest_product(self, n):
        ps = [reduce(mul, l, 1) for l in self.slices(n)]
        if ps:
            return max(ps)
        return 0

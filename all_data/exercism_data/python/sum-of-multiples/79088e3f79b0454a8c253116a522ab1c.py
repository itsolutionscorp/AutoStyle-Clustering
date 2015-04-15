class SumOfMultiples:
    def __init__(self, *args):
        if args:
            self.nums = list(args)
        else:
            self.nums = [3, 5]
            
    def div_by(self, div):
        for n in self.nums:
            if not div % n:
                return True
        return False

    def to(self, num):
        nums = filter(self.div_by, range(2, num))
        return sum(nums)

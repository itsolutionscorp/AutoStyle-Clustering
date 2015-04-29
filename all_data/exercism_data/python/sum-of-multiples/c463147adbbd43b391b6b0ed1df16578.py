class SumOfMultiples:
    def __init__(self, *args):
        self.nums = [3, 5]
        if len(args) > 0:
            self.nums = args
        
    def to(self, n):
        valid_values = []
        for num in self.nums:
            i = num
            while i < n:
                valid_values.append(i)
                i += num
        return sum(set(valid_values))

class SumOfMultiples():
    def __init__(self, *args):
        if not args:
            self.nums = (3, 5)
        else:
            self.nums = args

    def to(self, limit):
        total = 0
        multiple_list = []
        for x in self.nums:
            for y in range(x, limit, x):
                if y not in multiple_list:
                    total += y
                    multiple_list.append(y)
                

        return total

class SumOfMultiples():

    def __init__(self, *args):
        self.nums = args

    def to(self, num):

        nums_length = len(self.nums)

        if not nums_length:
            self.nums = (3,5)

        sum = 0

        for x in range(num):

            for num in self.nums:
                if not x % num:
                    sum += x
                    break

        return sum

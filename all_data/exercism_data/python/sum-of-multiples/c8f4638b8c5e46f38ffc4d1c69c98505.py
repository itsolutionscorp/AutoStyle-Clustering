from functools import reduce


class SumOfMultiples():

    def __init__(self, *args):
        self.values = [int(i) for i in args] if len(args) else [3, 5]

    def to(self, max_num) -> int:
        max_num -= 1
        total = 0
        for number in self.values:
            total += number * (max_num//number) * (max_num//number + 1) // 2
        multiplee = reduce(lambda x, y: x * y, self.values)
        total -= multiplee * (max_num//multiplee) * (max_num // multiplee + 1) // 2
        return total

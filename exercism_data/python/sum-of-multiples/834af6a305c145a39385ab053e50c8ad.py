from functools import reduce


class SumOfMultiples():

    def __init__(self, *args):
        self.values = args if len(args) else [3, 5]

    def to(self, max_num) -> int:
        max_num -= 1
        total = 0
        #calculate the sum of all the numbers given.
        for number in self.values:
            total += number * (max_num//number) * (max_num//number + 1) // 2

        #remove all doubles from total
        multiplee = reduce(lambda x, y: x * y, self.values)
        total -= multiplee * (max_num//multiplee) * (max_num // multiplee + 1) // 2
        return total

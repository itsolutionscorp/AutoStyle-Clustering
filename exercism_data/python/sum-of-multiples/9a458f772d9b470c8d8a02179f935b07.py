#!/usr/bin/python
class SumOfMultiples():
    def __init__(self, *args):
        if args == ():
            self.args = [3, 5]
        else:
            self.args = args

    def is_multiple_of_args(self, x):
        return len(filter(lambda w: w == 0, [x%y for y in self.args])) > 0

    def to(self, n):
        return sum(filter(lambda x: self.is_multiple_of_args(x), range(n)))

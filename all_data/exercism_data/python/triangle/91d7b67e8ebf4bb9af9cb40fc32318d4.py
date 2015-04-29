import collections


class Triangle:

    def __init__(self, *args):
        self.tupl = args
        if sum(self.tupl) - max(self.tupl) <= max(self.tupl):
            raise TriangleError('Wrong input')

    def kind(self):
        c = collections.Counter(self.tupl)
        leng = len(list(c))
        if leng == 1:
            return 'equilateral'
        elif leng == 2:
            return 'isosceles'
        elif leng == 3:
            return 'scalene'


class TriangleError(Exception):
    pass

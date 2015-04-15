from itertools import permutations, combinations

TRITYPES = { 0: 'scalene',
             1: 'isosceles',
             3: 'equilateral' }

class Triangle(object):
    def __init__(self, *corners):

        if any(x == 0.0 for x in corners):
            raise TriangleError("Triangle with zero size")
        if any(x < 0.0 for x in corners):
            raise TriangleError("Triangle with negative side")
        if any(a+b <= c for a,b,c in permutations(corners)):
            raise TriangleError("Side lengths violate triangle inequality")

        self._tritype = TRITYPES[sum(a==b for a,b in combinations(corners,2))]

    def kind(self):
        return self._tritype

class TriangleError(Exception):
    def __init__(self, value):
        self.value = value
    def __str__(self):
        return str(self.value)

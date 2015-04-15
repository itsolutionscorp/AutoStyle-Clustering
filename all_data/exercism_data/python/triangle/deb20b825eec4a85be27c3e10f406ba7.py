from itertools import permutations

class Triangle(object):
    def __init__(self, *corners):

        if any(x == 0.0 for x in corners):
            raise TriangleError("Triangle with zero size")
        if any(x < 0.0 for x in corners):
            raise TriangleError("Triangle with negative side")
        if any(a+b <= c for a,b,c in permutations(corners)):
            raise TriangleError("Side lengths violate triangle inequality")

        if all(a == b for a,b,c in permutations(corners)):
            self.tritype = "equilateral"
        elif any(a == b for a,b,c in permutations(corners)):
            self.tritype = "isosceles"
        else: 
            self.tritype = "scalene"

    def kind(self):
        return self.tritype

class TriangleError(Exception):
    def __init__(self, value):
        self.value = value
    def __str__(self):
        return str(self.value)

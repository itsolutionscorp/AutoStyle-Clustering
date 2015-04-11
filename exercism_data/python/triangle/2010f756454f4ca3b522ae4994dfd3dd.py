
class TriangleError(Exception):
    pass

UNIQUE_SIDES = {1: "equilateral",
                2: "isosceles",
                3: "scalene" }

class Triangle:
    def __init__(self, *sides):
        a, b, c = sorted(sides)
        if ((min(sides) <= 0) or
            (a + b <= c)):
            raise TriangleError
        self._kind = UNIQUE_SIDES[len(set(sides))]

    def kind(self):
        return self._kind

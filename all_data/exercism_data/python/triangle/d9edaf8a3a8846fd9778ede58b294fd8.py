class Triangle(object):
    def __init__(self, a, b, c):
        self.sides = [a, b, c]
        self._check()

    def _check(self):
        if not all(s > 0 for s in self.sides):
            raise TriangleError("Sides must be greater than zero.")
        a, b, c = sorted(self.sides)
        if not (a + b > c):
            raise TriangleError("Input violates triangle inequality.")

    def kind(self):
        n = len(set(self.sides)) - 1
        return ["equilateral", "isosceles", "scalene"][n]


class TriangleError(Exception):
    pass

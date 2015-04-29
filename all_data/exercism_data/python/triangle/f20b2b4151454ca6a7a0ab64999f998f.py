class Triangle(object):

    def __init__(self, a, b, c):
        self.sides = (a, b, c)
        self._validate()

    def _validate(self):
        a, b, c = sorted(self.sides)
        if a <= 0 or b <= 0 or c <= 0:
            raise TriangleError("All triangle sides must be > 0.")
        if a + b <= c:
            raise TriangleError("Triangle inequality violated.")

    def kind(self):
        unique_sides = len(set(self.sides))
        return {
            1: "equilateral",
            2: "isosceles",
            3: "scalene"
        }[unique_sides]


class TriangleError(Exception):
    pass

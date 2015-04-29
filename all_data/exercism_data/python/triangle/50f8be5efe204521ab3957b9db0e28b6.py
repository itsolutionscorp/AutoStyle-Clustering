class Triangle():
    def __init__(self, *args):
        self.sides = args[:3]
        self.test()

    def test(self):
        sides = a, b, c = self.sides
        sums = b + c, a + c, a + b
        if any(su <= si for (su, si) in zip(sums, sides)):
            raise TriangleError

    def kind(self):
        dim = len(set(self.sides))
        if dim == 1:
            return "equilateral"
        if dim == 2:
            return "isosceles"
        return "scalene"


class TriangleError(ValueError):
    pass

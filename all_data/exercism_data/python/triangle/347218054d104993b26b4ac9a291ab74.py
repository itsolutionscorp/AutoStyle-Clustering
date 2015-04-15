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
        a, b, c = self.sides
        if a == b == c:
            return "equilateral"
        if a == b or b == c or c == a:
            return "isosceles"
        return "scalene"


class TriangleError(ValueError):
    pass

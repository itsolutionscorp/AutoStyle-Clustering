class TriangleError(BaseException):
    pass

class Triangle:
    def __init__(self, a, b, c):
        sides = [a, b, c]
        sides = sorted(sides)
        if sides[0] + sides[1] <= sides[2]:
            raise TriangleError

        self.sides = sides

    def kind(self):
        a,b,c = tuple(self.sides)
        if a == b == c:
            return "equilateral"

        if a == b or b == c:
            return "isosceles"

        if a != b and b != c:
            return "scalene"

        return ""

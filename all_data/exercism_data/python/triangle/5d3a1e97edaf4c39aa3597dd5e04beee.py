class TriangleError(ValueError):
    pass


class Triangle:

    def __init__(self, *sides):
        sides = sorted(sides)
        self.check(sides)
        self.sides = sides

    def check(self, sides):
        for side in sides:
            if side <= 0:
                raise TriangleError("invalid triangle")

        if sides[0] + sides[1] <= sides[2]:
            raise TriangleError("invalid triangle!")

    def kind(self):
        l = len(set(self.sides))

        if l == 1:
            return "equilateral"
        elif l == 2:
            return "isosceles"
        else:
            return "scalene"

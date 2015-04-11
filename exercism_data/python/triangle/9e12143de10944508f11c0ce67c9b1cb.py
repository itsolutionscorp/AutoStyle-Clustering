class Triangle(object):
    def __init__(self, side1, side2, side3):
        self.sides = sorted([side1,side2,side3])
        if self.sides[2] >= sum(self.sides[:2]):
            raise TriangleError("The two smaller sides of the triangle must sum to be greater than the largest side.")

    def kind(self):
        if self.sides[0] == self.sides[2]:
            return 'equilateral'
        elif self.sides[0] == self.sides[1] or self.sides[1] == self.sides[2]:
            return 'isosceles'
        else:
            return 'scalene'

class TriangleError(Exception):
    pass

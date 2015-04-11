class Triangle:
    """ Triangle determines the kind of triangle that is input or 
    raises an error if it is invalid """

    def __init__(self, a, b, c):
        self.a = a
        self.b = b
        self.c = c
        self.isValid()

    def kind(self):
        if self.a == self.b and self.b == self.c:
            return "equilateral"
        elif self.a == self.b or self.b == self.c or self.a == self.c:
            return "isosceles"
        else:
            return "scalene"

    def isValid(self):
        if self.a <= 0 or self.b <= 0 or self.c <= 0:
            raise TriangleError("Illegal size sides")
        sides = sorted([self.a, self.b, self.c])
        if sides[0] + sides[1] <= sides[2]:
            raise TriangleError("Violates Triangle Inequality")

class TriangleError(Exception):
    """ TriangleError indicates that a triangle is not valid """
    def __init__(self, value):
        self.value = value

    def __str__(self):
        return repr(self.value)

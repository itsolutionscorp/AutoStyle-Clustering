class Triangle:
    def __init__(self, a, b, c):
        self.sides = sorted([a, b, c])
    def kind(self):
        if self.sides[0] == self.sides[2]:
            return 'equilateral'
        elif self.sides[0] == self.sides[1] or self.sides[1] == self.sides[2]:
            return 'isosceles'
        else:
            return 'scalene'

class TriangleError(Exception):
    def __init__(self, value):
        self.value = value
    def __str__(self):
        return repr(self.value)
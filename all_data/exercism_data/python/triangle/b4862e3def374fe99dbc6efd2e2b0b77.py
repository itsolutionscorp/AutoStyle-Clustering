__author__ = 'angelo'

def get_triangle_type(sides):
    s,m,l = sorted(sides)
    if any(x <= 0 for x in sides) or s+m <= l:
        raise TriangleError("Not a valid triangle.")
    if all(x == sides[0] for x in sides):
        return 'equilateral'
    if sides[0] == sides[1] or sides[0] == sides[2] or sides[1] == sides[2]:
        return 'isosceles'
    else:
        return 'scalene'

class Triangle:

    def __init__(self, a,b,c):
        self.sides = [a,b,c]
        try:
            self.type = get_triangle_type(self.sides)
        except TriangleError, e:
            raise TriangleError(e)

    def kind(self):
        return self.type


class TriangleError(Exception):
    def __init__(self, value):
        self.value = value
    def __str__(self):
        return repr(self.value)

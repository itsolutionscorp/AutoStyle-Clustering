class TriangleError(Exception):
    pass

class Triangle(object):
    def __init__(self, a, b, c):
        if (a+b <= c) or (b+c <= a) or (a+c <= b):
            raise TriangleError()
        self.a, self.b, self.c = a, b, c

    def kind(self):
        if (self.a == self.b) and (self.b == self.c):
            return "equilateral"
        elif (self.a == self.b) or (self.b == self.c) or (self.a == self.c):
            return "isosceles"
        return "scalene"

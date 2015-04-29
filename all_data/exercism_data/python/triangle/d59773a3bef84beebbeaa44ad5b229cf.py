#!/usr/bin/env python

class TriangleError(ValueError):
    pass

class Triangle:
    def __init__(self, a, b, c):
        if a >= b + c or b >= a + c or c >= a + b:
            raise TriangleError
        self.a, self.b, self.c = a, b, c
    
    def kind(self):
        if self.a == self.b == self.c:
            return "equilateral"
        elif self.a  == self.b or self.b == self.c or self.c == self.a:
            return "isosceles"
        else:
            return "scalene"

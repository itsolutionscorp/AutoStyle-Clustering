class Triangle(object):
    
    def __init__(self, a, b, c):
        self.a, self.b, self.c  = sorted([a, b, c])
        for i in (self.a, self.b, self.c):
            if i <= 0:
                raise TriangleError("Cannot have side with non-positive length")
        if self.a + self.b <= self.c:
            raise TriangleError("Cannot have side longer than sum of others")
    
    def kind(self):
        if self.a == self.b == self.c:
            return 'equilateral'
        elif self.a == self.b or self.a == self.c or self.b == self.c:
            return 'isosceles'
        return 'scalene'
    
class TriangleError(Exception):

    def __init__(self, message):
        super(TriangleError, self).__init__(message)

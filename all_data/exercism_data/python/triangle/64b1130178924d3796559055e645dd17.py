class TriangleError(Exception):
    def __init__(self, value):
        self.value = value

    def __str__(self):
        return repr(self.value)


class Triangle:
    def __init__(self, a, b, c):
        self.dims = sorted([a, b, c])
        self.validatetriangle()

    def kind(self):
        if self.dims[0] == self.dims[2]:
            return 'equilateral'
        if self.dims[1] == self.dims[2]:
            return 'isosceles'
        return 'scalene'

    def validatetriangle(self):
        if self.dims[0] <= 0:
            raise TriangleError('invalid triangle')
        if self.dims[0] + self.dims[1] <= self.dims[2]:
            raise TriangleError('invalid triangle')

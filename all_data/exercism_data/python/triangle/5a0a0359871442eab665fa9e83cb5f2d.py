class Triangle:
    def __init__(self, x, y, z):
        (a, b, c) = sorted([x, y, z])

        if a <= 0 or a + b <= c:
            raise TriangleError('Invalid lengths for a triangle') 

        self.sides = (a, b, c)

    def kind(self):
        result = 'scalene'

        if len(set(self.sides)) == 2:
            result = 'isosceles'
        if len(set(self.sides)) == 1:
            result = 'equilateral'

        return result
        
class TriangleError(Exception):
    pass

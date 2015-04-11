class TriangleError(Exception):
    pass

class Triangle:
    def __init__(self, side1, side2, side3):
        sort_val = sorted([side1, side2, side3])
        self.side1 = sort_val[0]
        self.side2 = sort_val[1]
        self.side3 = sort_val[2]
        if self.side3 >= (self.side1 + self.side2):
            raise TriangleError

    def kind(self):
        if self.side3 >= (self.side1 + self.side2):
            raise TriangleError
        elif self.side1 == self.side2 and self.side3 == self.side1:
            return 'equilateral'
        elif self.side3 == self.side2:
            return 'isosceles'
        else:
            return 'scalene'

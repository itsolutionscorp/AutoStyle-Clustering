class Triangle():

    def __init__(self,s1,s2,s3):
        self.s1 = s1
        self.s2 = s2
        self.s3 = s3

        if any([s1 + s2 <= s3,
                s2 + s3 <= s1,
                s3 + s1 <= s2,]):
            raise TriangleError, "Sides given do not constitute a triangle."

    def kind(self,):
        if self.s1 == self.s2 == self.s3:
            return 'equilateral'
        elif any([self.s1 == self.s2, self.s2 == self.s3, self.s3 == self.s1]):
            return 'isosceles'
        else:
            return 'scalene'


class TriangleError(Exception):
    def __init__(self,message):
        super(TriangleError, self).__init__(message)

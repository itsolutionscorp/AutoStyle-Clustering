'''exer triangle'''

class TriangleError(Exception):
    '''raised when an exception is emitted from Triangle'''
    pass

class Triangle(object):
    '''triangle classifier'''

    def __init__(self, a, b, c):
        '''init the object'''
        # guard
        self._guard(a, b, c)
        self._lengths = (a, b, c)

    @staticmethod
    def _guard(a_b, b_c, c_a):
        """guard against invalid triangle information"""
        # sort from smallest to largest to simplify tests
        small, middle, large = [side for side in sorted([a_b, b_c, c_a])]
        if small + middle <= large:
            raise TriangleError('violates triangle inequality')
        if small <= 0: # or b <= 0 or c <= 0:
            raise TriangleError("Triangle can't have no or negative size")

    def kind(self):
        '''return the kind of triangle'''
        # evaluate
        a_b, b_c, c_a = self._lengths
        if a_b == b_c == c_a:
            kind = 'equilateral'
        elif a_b == b_c or b_c == c_a or c_a == a_b:
            kind = 'isosceles'
        else:
            kind = 'scalene'
        return kind

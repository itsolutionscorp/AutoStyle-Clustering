'''exer triangle'''

class TriangleError(Exception):
    '''raised when an exception is emitted from Triangle'''
    pass

class Triangle(object):
    '''triangle classifier'''

    def __init__(self, a, b, c):
        '''init the object'''
        # init
        self._kind = 'Unknown'
        # guard
        if a+b <= c or b+c <= a or a+c <= b:
            raise TriangleError('violates triangle inequality')
        if a <= 0 or b <= 0 or c <= 0:
            raise TriangleError("Triangle can't have no or negative size")
        # evaluate
        if a == b == c:
            self._kind = 'equilateral'
        elif a == b or b == c or c == a:
            self._kind = 'isosceles'
        else:
            self._kind = 'scalene'

    def kind(self):
        '''return the kind of triangle'''
        return self._kind

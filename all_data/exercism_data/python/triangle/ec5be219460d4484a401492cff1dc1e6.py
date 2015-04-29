class TriangleError(Exception):
    pass

class Triangle():
    
    SIDES = 3
    
    def __init__(self,*sides):
        self.sides = self._check_sides(sorted(sides))
        
        
    def _check_sides(self,sides):
        try:
            sides = map(float, sides)
        except:
            raise TriangleError('Sides must be real numbers.')
        if not len(sides) == self.SIDES:
             raise TriangleError('Must provide three valid sides.')
        if not all([side > 0 for side in sides]) or not sides[0] + sides[1] > sides[2]:
            raise TriangleError('Invalid value(s) provided for triangle side(s).')
            
        return sides
        
    def kind(self):
        a,b,c = self.sides
        if a == b and b == c:
            return 'equilateral'
            
        if a == b or b == c:
            return 'isosceles'
            
        return 'scalene'

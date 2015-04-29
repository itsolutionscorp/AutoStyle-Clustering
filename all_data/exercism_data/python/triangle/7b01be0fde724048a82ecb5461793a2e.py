class TriangleError(Exception):
    pass

class Triangle():
    
    """ Triangle Class 
        Validates input (a,b,c) and stores the type
        to be called with the kind() method
    """
    
    SIDES = 3
    
    def __init__(self,*sides):
        
        self.sides = self._check_sides(sorted(sides))
        self.__type = self._determine_type()
        
        
    def _check_sides(self,sides):
        """ Validate input is a real number and sides are
            legal values for a triangle."""
            
        if not len(sides) == self.SIDES:
             raise TriangleError('Must provide three valid sides.')
             
        try:
            sides = map(float, sides)
        except:
            raise TriangleError('Sides must be real numbers.')
            
        if not sides[0] + sides[1] > sides[2]:
            raise TriangleError('Invalid value(s) provided for triangle side(s).')
            
        return sides
        
    def _determine_type(self):
        """ Determine type of Triangle with valid sides """
        a,b,c = self.sides
        if a == b == c:
            return 'equilateral'
            
        if a == b or b == c:
            return 'isosceles'
            
        return 'scalene'
        
    def kind(self):
        return self.__type

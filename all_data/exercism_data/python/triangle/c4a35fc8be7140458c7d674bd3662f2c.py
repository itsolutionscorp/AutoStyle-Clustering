TRIANGLE_KIND_EQUILATERAL = "equilateral"
TRIANGLE_KIND_ISOSCELES = "isosceles"
TRIANGLE_KIND_SCALENE = "scalene"


class TriangleError(ValueError):
    """Raised if trying to create an invalid triangle."""
    pass


class Triangle:

    """A Triangle"""

    def __init__(self, a, b, c):
        """Create new triangle from given edges a, b and c.

        Raises:
          TriangleError: If given edges do not conform to
                         the triangle inequality.
        """
        if a >= b + c or b >= a + c or c >= a + b:
            raise TriangleError
        self.a, self.b, self.c = a, b, c

    def kind(self):
        """Return which kind of triangle this is."""
        distinct_edges = len(set([self.a, self.b, self.c]))
        if distinct_edges == 1:
            return TRIANGLE_KIND_EQUILATERAL
        elif distinct_edges == 2:
            return TRIANGLE_KIND_ISOSCELES
        else:
            return TRIANGLE_KIND_SCALENE

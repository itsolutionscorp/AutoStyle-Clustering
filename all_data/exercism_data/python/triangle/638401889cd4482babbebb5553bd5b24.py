"""A triangle."""


class TriangleError(Exception):
    """Raised when the triangle inequality fails."""
    pass


class Triangle(object):
    """A triangle."""

    def __init__(self, x, y, z):
        """Create a triangle.

        Args:
            x: The length of a side of a triangle.
            y: The length of a side of a triangle.
            z: The length of a side of a triangle.

        Raises:
            TriangleError: The triangle inequality failed.
        """
        if x + y <= z or x + z <= y or y + z <= x:
            raise TriangleError("The triangle inequality failed.")
        self.sides = (x, y, z)

    def kind(self):
        """Return the kind of the triangle.

        Returns:
            str. "equilateral", "isosceles", or "scalene".
        """
        if self.sides[0] == self.sides[1] == self.sides[2]:
            return "equilateral"
        elif (self.sides[0] == self.sides[1] or
              self.sides[0] == self.sides[2] or
              self.sides[1] == self.sides[2]):
            return "isosceles"
        else:
            return "scalene"

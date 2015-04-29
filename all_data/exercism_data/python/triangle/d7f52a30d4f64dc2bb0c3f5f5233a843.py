class Triangle:

    TYPES = ['equilateral', 'isosceles', 'scalene']

    def __init__(self, *sides):
        self.sides = sides
        self._validate(self.sides)

    def kind(self):
        unique = self._uniq()
        return self.TYPES[len(unique) - 1]

    def _validate(self, sides):
        if any(side < 0 for side in self.sides) or not self._meets_triangle_equality():
            raise TriangleError()

    def _uniq(self):
        return list(set(self.sides))

    def _meets_triangle_equality(self):
        ordered = sorted(self.sides)
        return ordered[0] + ordered[1] > ordered[2]


class TriangleError(Exception):
    pass

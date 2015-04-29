class Triangle:

    # Triangle types are looked up by the number of unique sides
    TRIANGLE_TYPES = {
        0: 'equilateral',
        1: 'isosceles',
        2: 'scalene'
    }

    def __init__(self, *sides):
        self.sides = sides
        self._validate(self.sides)

    def kind(self):
        unique = self._uniq()
        return self.TRIANGLE_TYPES[len(unique) - 1]

    def _uniq(self):
        return set(self.sides)

    def _validate(self, sides):
        if (not self._has_three_sides() or
                self._has_negative_side() or not
                self._meets_triangle_equality()):
                    raise TriangleError()

    def _has_three_sides(self):
        return len(self.sides) == 3

    def _has_negative_side(self):
        return any(side < 0 for side in self.sides)

    def _meets_triangle_equality(self):
        ordered = sorted(self.sides)
        return ordered[0] + ordered[1] > ordered[2]


class TriangleError(Exception):
    pass

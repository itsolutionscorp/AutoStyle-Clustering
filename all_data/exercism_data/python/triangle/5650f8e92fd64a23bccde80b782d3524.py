class TriangleError(Exception):
    pass

class Triangle(object):
    def __init__(self, *sides):
        self.sides = sides
        self._check_impossible_side()
        self._check_inequality()

    def kind(self):
        distinct_lengths = len(set(self.sides))
        return ["equilateral", "isosceles", "scalene"][distinct_lengths-1]

    def _check_impossible_side(self):
        # If any length is non-positive, so will be the shortest side:
        if min(self.sides) <= 0:
            raise TriangleError("All sides must have a positive length.")

    def _check_inequality(self):
        # Inequality means: a + b > c for any permutation of a, b, c.
        # However, if this is not fulfilled, c must be the longest side,
        # and multiplying by 2 we can simplify to:
        if sum(self.sides) <= 2 * max(self.sides):
            raise TriangleError("The combined length of the two shortest"
                                " sides must be greater than the length"
                                " of the longest side.")

    pass

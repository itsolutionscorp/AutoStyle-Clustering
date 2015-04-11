class TriangleError(Exception):
    pass

class Triangle(object):
    descriptions = ("equilateral", "isosceles", "scalene")

    def __init__(self, *sides):
        self.sides = sides
        self._check_inequality()

    def kind(self):
        distinct_lengths = len(set(self.sides))
        return Triangle.descriptions[distinct_lengths-1]

    def _check_inequality(self):
        # Inequality means: a + b > c for any permutation of a, b, c.
        # However, if this is not fulfilled, c must be the longest side,
        # and we can simplify to:
        if sum(self.sides) <= 2 * max(self.sides):
            raise TriangleError("The combined length of the two shortest"
                                " sides must be greater than the length"
                                " of the longest side.")

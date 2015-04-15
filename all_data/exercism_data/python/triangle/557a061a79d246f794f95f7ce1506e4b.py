class TriangleError(Exception):
    pass


class Triangle:
    def __init__(self, x, y, z):
        if (x <= 0 or y <= 0 or z <= 0 or
            (x + y - z) * (y + z - x) * (z + x - y) <= 0):
            raise TriangleError
        self._kind = {
            1: "equilateral", 2: "isosceles", 3: "scalene"}[len({x, y, z})]

    def kind(self):
        return self._kind

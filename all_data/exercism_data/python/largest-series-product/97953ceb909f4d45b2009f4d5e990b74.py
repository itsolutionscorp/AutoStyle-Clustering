import functools

class Series:
    def __init__(self, string):
        self.string = string

    def slices(self, n):
        self._validate(n)

        lst = self._lst()
        collector = []
        for i in range(0, len(lst)):
            inner = []
            for j in range(0, n):
                if i - j >= 0 :
                    inner.insert(0, lst[i - j])
                if len(inner) == n:
                    collector.append(inner)
        return collector

    def largest_product(self, n):
        if n == 0:
            return 1
        slices = self.slices(n)
        return max(list([functools.reduce((lambda x, y: x * y), slice) for slice in slices]))

    def _validate(self, n):
        if n > len(self.string):
            raise ValueError("Invalid slice length for this series: " + str(n))

    def _lst(self):
        return list(map(int, list(self.string)))

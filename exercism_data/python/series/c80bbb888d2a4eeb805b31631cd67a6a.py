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

    def _validate(self, n):
        if n > len(self.string) or n == 0:
            raise ValueError("Invalid slice length for this series: " + str(n))

    def _lst(self):
        return map(int, list(self.string))

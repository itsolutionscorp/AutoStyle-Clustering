class Series(object):
    def __init__(self, string):
        self.string = string

    def slices(self, size):
        if size > len(self.string) or size < 0:
            raise ValueError('Invalid slice length for this series: {}'.format(size))

        if size == 0:
            return []

        return [map(int, list(self.string[i:i + size])) for i in range(len(self.string) + 1 - size)]

    def largest_product(self, size):
        slices = self.slices(size)

        if len(slices) == 0:
            return 1

        pmax = 0
        for s in slices:
            prod = 1
            for e in s:
                prod *= e
            if prod > pmax:
                pmax = prod

        return pmax

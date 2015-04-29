class Series(object):
    def __init__(self, string):
        self.string = string

    def slices(self, n):
        if n > len(self.string) or n <= 0:
            raise ValueError('Invalid slice length for this series: {}'.format(n))

        results = []
        for i in range(0, len(self.string) + 1 - n):
            results.append(map(int, list(self.string[i:i + n])))
        return results

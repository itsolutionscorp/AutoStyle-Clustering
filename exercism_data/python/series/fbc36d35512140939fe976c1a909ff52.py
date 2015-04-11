class Series(object):
    def __init__(self, digits):
        self.digits = map(int, digits)

    def slices(self, n):
        if not 0 < n <= len(self.digits):
            raise ValueError('Invalid slice length for this series: %d' % n)
        return [self.digits[i: i + n] for i in range(len(self.digits) - n + 1)]

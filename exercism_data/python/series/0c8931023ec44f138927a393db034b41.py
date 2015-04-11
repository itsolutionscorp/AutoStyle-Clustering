class Series(object):
    def __init__(self, string):
        self.digits = [int(c) for c in string]

    def slices(self, size):
        if size > len(self.digits) or size == 0:
            raise ValueError('Invalid slice length for this series: {}'.format(size))
        else:
            return [self.digits[x:x+size] for x in range(len(self.digits) + 1 - size)]

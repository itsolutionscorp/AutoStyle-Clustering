class Series(object):
    def __init__(self, string):
        self.string = string

    def slices(self, size):
        if size > len(self.string) or size == 0:
            raise ValueError('Invalid slice length for this series: {}'.format(size))
        else:
            return [[int(y) for y in self.string[x:x+size]] for x in range(len(self.string)+1-size)]

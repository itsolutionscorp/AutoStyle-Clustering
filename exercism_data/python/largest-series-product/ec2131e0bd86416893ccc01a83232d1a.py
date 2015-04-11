def product(some_list):
    p = 1
    for i in some_list:
        p = i * p
    return p


class Series(object):
    def __init__(self, string):
        self.digits = [int(c) for c in string]

    def slices(self, size):
        if size > len(self.digits):
            raise ValueError('Invalid slice length for this series: {}'.format(size))
        else:
            return [self.digits[x:x+size] for x in range(len(self.digits) + 1 - size)]

    def largest_product(self, size):
        return sorted([product(s) for s in self.slices(size)])[-1]

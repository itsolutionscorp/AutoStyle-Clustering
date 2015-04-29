__author__ = 'emiller42'


class SumOfMultiples:

    bases = [3, 5]

    def __init__(self, *args):

        #override default multiples if arguments present
        if len(args) > 0:
            self.bases = []
            for a in args:
                self.bases.append(a)
        else:
            self.bases = SumOfMultiples.bases

    def to(self, value):
        total = 0
        for i in range(1, value):
            if self.is_multiple(i):
                total += i
        return total

    # returns true if the supplied value
    # is a multiple of any base.
    def is_multiple(self, value):
        for base in self.bases:
            if value % base == 0:
                return True

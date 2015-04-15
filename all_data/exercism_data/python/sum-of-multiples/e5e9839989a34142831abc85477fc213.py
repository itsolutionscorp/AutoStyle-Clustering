__author__ = 'emiller42'


class SumOfMultiples:

    multiples = [3, 5]

    def __init__(self, *args):

        #override default multiples if arguments present
        if len(args) > 0:
            self.multiples = []
            for a in args:
                self.multiples.append(a)
        else:
            self.multiples = SumOfMultiples.multiples

    def to(self, value):
        # initialize the return value
        total = 0

        # loop through value range.
        # for each value, loop through multiples
        # if the value is a multiple, add it to the total
        # and break out of that iteration.
        # (Values should not be double counted)
        for i in range(1, value):
            for multiple in self.multiples:
                if i % multiple == 0:
                    total += i
                    break
        return total

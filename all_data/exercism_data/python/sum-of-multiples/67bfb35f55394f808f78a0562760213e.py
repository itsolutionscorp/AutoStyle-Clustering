class SumOfMultiples(object):

    def __init__(self, *args):
        if len(args) == 0:
            self.args = (3, 5)
        else:
            self.args = args

    def to(self, limit):
        multiples = []
        for arg in self.args:
            multiples += [i for i in range(arg,
                                           limit, arg) if not i in multiples]
        return sum(multiples)

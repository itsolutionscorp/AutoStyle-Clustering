import itertools
duck = "01234"
class slices(object):
    def __init__(self, test, test2):
        for i in itertools.permutations(test, test2):
            return ''.join(i)
